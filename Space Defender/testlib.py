import pygame
import math
pygame.mixer.init()

# Load images
RED_SPACE_SHIP = pygame.transform.scale(pygame.image.load(r'assets\\images\\red saucer.png') , (50 , 35))
GREEN_SPACE_SHIP = pygame.transform.scale(pygame.image.load(r'assets\\images\\fish fighter orange.png'), (65 , 35))
BLUE_SPACE_SHIP = pygame.transform.scale(pygame.image.load(r'assets\\images\\saucer.png'), (50 , 35))
# Player player
STANDART_SPACE_SHIP = pygame.image.load(r'assets\\images\\ship2.png')

# Lasers


STANDART_LASER = pygame.image.load(r'assets/images/player_bullet.png')
ENEMY_STANDART_LASER = pygame.transform.scale(pygame.image.load(r'assets\\images\\laser-bolts.png'), (15, 15))

point_gain = pygame.mixer.Sound(r'assets\\sounds\\FunkyExplosion.wav')  # patlama sesi ekle
point_gain.set_volume(2)

class Laser:
    def __init__(self, x, y, img, angle=0, speed=8):
        self.x = x
        self.y = y
        self.img = img
        self.mask = pygame.mask.from_surface(self.img)
        self.angle = math.radians(angle)
        self.speed = speed
    def draw(self, window):
        window.blit(self.img, (self.x, self.y))

    def move(self, vel = None):
        self.x += math.cos(self.angle) * self.speed
        self.y += math.sin(self.angle) * self.speed

    def off_screen(self, width):
        return not(self.x <= width and self.x >= 0)

    def collision(self, obj):
        return collide(self, obj)


class Ship:
    COOLDOWN = 10

    def __init__(self, x, y , width , health=100):
        self.x = x
        self.y = y
        self.health = health
        self.width = width
        self.ship_img = None
        self.laser_img = None
        self.lasers = []
        self.cool_down_counter = 0
        self.fire = pygame.mixer.Sound(r'assets\\sounds\\shot-1.ogg')

    def draw(self, window):
        window.blit(self.ship_img, (self.x, self.y))
        for laser in self.lasers:
            laser.draw(window)

    def move_lasers(self, vel, obj):
        self.cooldown()
        for laser in self.lasers:
            laser.move(vel)
            if laser.off_screen(self.width):
                self.lasers.remove(laser)
            elif laser.collision(obj):
                if hasattr(obj, "armor_active") and obj.armor_active:
                    pass
                else:
                    obj.health -= 10
                self.lasers.remove(laser)

    def cooldown(self):
        if self.cool_down_counter >= self.COOLDOWN:
            self.cool_down_counter = 0
        elif self.cool_down_counter > 0:
            self.cool_down_counter += 1

    def shoot(self):
        if self.cool_down_counter == 0:
            laser = Laser(self.x + 65, self.y + 28, self.laser_img)
            self.lasers.append(laser)
            self.cool_down_counter = 1
            self.fire.set_volume(2)
            self.fire.play(0)

    def get_width(self):
        return self.ship_img.get_width()

    def get_height(self):
        return self.ship_img.get_height()


class Player(Ship):
    def __init__(self, x, y, width , health=100):
        super().__init__(x, y , width, health)
        self.armor_active = False
        self.armor_ready = False
        self.armor_timer = 0
        self.default_image = pygame.image.load(r'assets\\images\\ship2.png')
        self.ship_img = STANDART_SPACE_SHIP
        self.mask = pygame.mask.from_surface(self.ship_img)
        self.laser_img = STANDART_LASER
        self.max_health = health
        self.SHIELD_IMG = pygame.image.load(r'assets\\images\\shield.png')
        self.triple_active = False

    def move_lasers(self, vel, objs , point , explosions):
        self.cooldown()
        for laser in self.lasers:
            laser.move(vel)
            if laser.off_screen(self.width):
                self.lasers.remove(laser)
            else:
                for obj in objs:
                    if laser.collision(obj):
                        obj.health -= 10
                        if obj.health <= 0:
                            objs.remove(obj)
                            if isinstance(obj, Enemy):
                                point += 100
                            elif isinstance(obj, Boss):
                                point += 1000
                            explosions.append(Explosion((obj.x - 15 , obj.y - 15), 5, 5))
                            point_gain.play(0)

                        if laser in self.lasers:
                            self.lasers.remove(laser)
        return point

    def draw(self, window):
        super().draw(window)
        if self.armor_active:
            shield_x = self.x 
            shield_y = self.y - 20
            window.blit(self.SHIELD_IMG, (shield_x, shield_y))
        self.healthbar(window)

    def healthbar(self, window):
        pygame.draw.rect(window, (255, 0, 0), (self.x, self.y + self.ship_img.get_height() + 10, self.ship_img.get_width(), 10))
        pygame.draw.rect(window, (0, 255, 0), (self.x, self.y + self.ship_img.get_height() + 10, self.ship_img.get_width() * (self.health/self.max_health), 10))

    def shoot(self):
        if self.cool_down_counter == 0:
            if self.triple_active:
                self.lasers.append(Laser(self.x + 65, self.y + 28, self.laser_img, angle=0))  # центр
                self.lasers.append(Laser(self.x + 65, self.y + 28, self.laser_img, angle=-20))  # вверх
                self.lasers.append(Laser(self.x + 65, self.y + 28, self.laser_img, angle=20))  # вниз
            else:
                self.lasers.append(Laser(self.x + 65, self.y + 28, self.laser_img))
            self.cool_down_counter = 1
            self.fire.set_volume(2)
            self.fire.play(0)


class Enemy(Ship):
    COLOR_MAP = {
        "red": (RED_SPACE_SHIP, ENEMY_STANDART_LASER),
        "green": (GREEN_SPACE_SHIP, ENEMY_STANDART_LASER),
        "blue": (BLUE_SPACE_SHIP, ENEMY_STANDART_LASER)
    }

    def __init__(self, x, y, width , color, health=10):
        super().__init__(x, y, width , health)
        self.ship_img, self.laser_img = self.COLOR_MAP[color]
        self.mask = pygame.mask.from_surface(self.ship_img)

    def move(self, vel):
        self.x -= vel  # değiştir

    def shoot(self):
        if self.cool_down_counter == 0:
            laser = Laser(self.x - 20, self.y, self.laser_img, angle=180)
      
            self.lasers.append(laser)
            self.cool_down_counter = 1


class Boss(Ship):

    def __init__(self, x, y, width , health=100):
        super().__init__(x, y, width , health)
        self.ship_img = pygame.transform.scale(pygame.image.load(r'assets\\images\\boss.png'), (100, 60))
        self.mask = pygame.mask.from_surface(self.ship_img)
        self.laser_img = pygame.image.load(r'assets\\images\\boss_bullet.png')
        self.max_health = health


    def move(self, vel):
        self.x -= vel  # değiştir

    def shoot(self):
        if self.cool_down_counter == 0:
            laser = Laser(self.x - 20, self.y + 25, self.laser_img, angle=180)
     
            self.lasers.append(laser)
            self.cool_down_counter = 1



class Explosion():
    def __init__(self, pos , countRange, explosion_image_range):
        self.explode_temp_index = 0
        self.countRange = countRange
        self.explosion_image_range = explosion_image_range
        self.counter = 0
        self.pos = pos

    def draw(self , WINDOW , explosion_images):
        if (self.counter == self.countRange):
            self.counter = 0
            self.explode_temp_index += 1
            if (self.explode_temp_index == self.explosion_image_range):
                return True
        self.counter += 1
        WINDOW.blit(explosion_images[self.explode_temp_index], (self.pos[0], self.pos[1]))
        return False


def collide(obj1, obj2):
    offset_x = obj2.x - obj1.x
    offset_y = obj2.y - obj1.y
    return obj1.mask.overlap(obj2.mask, (offset_x, offset_y)) != None