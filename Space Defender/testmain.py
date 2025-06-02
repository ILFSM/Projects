from turtle import width
import pygame
import math
import os
import pickle
from pygame import K_SPACE

import testlib
import random

pygame.font.init()
pygame.mixer.init()

WIDTH, HEIGHT = 1280, 640
WINDOW = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("version 4.3")
flag = False
bought = {"Armor (15s)": False, "Triple Shot": False}
# Background
# BG = pygame.transform.scale(pygame.image.load(os.path.join("assets", "background-black.png")), (WIDTH, HEIGHT))
BG = pygame.image.load(r'assets\\images\\img.png')
BG = pygame.transform.scale(BG, (WIDTH, HEIGHT))
BG_width = BG.get_width()
BG_rect = BG.get_bounding_rect()
clock = pygame.time.Clock()
FPS = 85
# Save the variable
#pickle.dump(variable, open("variableStoringFile.dat", "wb"))
# Load the variable
#variable = pickle.load(open("variableStoringFile.dat", "rb"))
main_font = pygame.font.Font(r'assets\\fonts\\Andromeda-Wyr1E.otf', 70)
lost_font = pygame.font.Font(r'assets\\fonts\\Andromeda-Wyr1E.otf', 50)
global money
money = 100
def main(bplayer2 , bMusic):
    global bought
    global money
    run = True
    max_point = pickle.load(open("variableStoringFile.dat", "rb"))
    level = 0
    lives = 5
    point = 0
    
    gain_healthy = pygame.mixer.Sound(r'assets\\sounds\\gain_health.ogg')
    gain_healthy.set_volume(3)

    game_sound = pygame.mixer.Sound(r'assets\\music\\level0.ogg')  # şimdilik iptal
    game_sound.set_volume(0.2)

    

    game_over = pygame.mixer.Sound(r'assets\\sounds\\game_over.ogg')
    game_over.set_volume(2)

    point_gain = pygame.mixer.Sound(r'assets\\sounds\\FunkyExplosion.wav')  # patlama sesi ekle
    point_gain.set_volume(2)


    enemies = []
    wave_length = 4
    enemy_vel = 2
    laser_vel = 8

    # explotion #
    explosions = []

    # fill explosions
    explosion_images = []
    explosion_image_range = 5
    countRange = 5
    for i in range(explosion_image_range):
        explosion_images.append(pygame.transform.scale((pygame.image.load(os.path.join('assets', 'images', 'explosion', f'{i}.png'))),(64, 64)))


    def draw_explosions():
        for exp in explosions:
            if (exp.draw(WINDOW , explosion_images)):
                explosions.remove(exp)


    player1 = testlib.Player(30, HEIGHT / 2, WIDTH)
    if(bplayer2 == True):
        player2 = testlib.Player(40 , HEIGHT / 2 , WIDTH)

    if bMusic == False: game_sound.set_volume(0)
    if bMusic == True: game_sound.set_volume(0.2)

    last_pos = pygame.mouse.get_pos()

    scroll0 = 0
    tiles = math.ceil(WIDTH / BG_width) + 1

    lost = False
    lost_count = 0

    if bought["Armor (15s)"]:
            player1.armor_ready = True
            
    if bought["Triple Shot"]:
            player1.triple_active = True


    def redraw_window():
        # draw text
        lives_label = lost_font.render(f"Lives: {lives}", 1, (255,255,255))
        level_label = lost_font.render(f"Level: {level}", 1, (255,255,255))
        point_label = lost_font.render(f"Score: {point}", 1, (255,255,255))

        WINDOW.blit(lives_label, (10, 10))
        WINDOW.blit(point_label, (500, 10))
        WINDOW.blit(level_label, (WIDTH - level_label.get_width() - 10, 10))

        for enemy in enemies:
            if isinstance(enemy , testlib.Enemy):
                enemy.draw(WINDOW)
            elif isinstance(enemy , testlib.Boss):
                if enemy.health > 0:
                    enemy.draw(WINDOW)

        draw_explosions()

        if player1.health > 0:
            player1.draw(WINDOW)

        if (bplayer2 == True):
            if player2.health > 0:
                player2.draw(WINDOW)

        if lost:
            game_sound.stop()
            lost_label = lost_font.render("You Lost!! ", 1, (255,255,255))
            WINDOW.blit(lost_label, (WIDTH/2 - lost_label.get_width()/2, 350))
            max_point = pickle.load(open("variableStoringFile.dat", "rb"))
            # your score
            point_label = lost_font.render("Your Score: " + str(point), 1, (255, 255, 255))
            WINDOW.blit(point_label, ((WIDTH / 2 - lost_label.get_width() / 2) - 50, 400))
            # biggest score
            point_label = lost_font.render("Best Score Ever: " + str(max_point), 1, (255, 255, 255))
            WINDOW.blit(point_label, ((WIDTH / 2 - lost_label.get_width() / 2) - 100, 450))
            player1.triple_active = False
            bought["Triple Shot"] = False
        pygame.display.update()
    
    while run:
        clock.tick(FPS)
        game_sound.play(0)
        keys = pygame.key.get_pressed()
        if keys[pygame.K_SPACE] and player1.armor_ready and not player1.armor_active:
            player1.armor_timer = pygame.time.get_ticks()
            player1.armor_active = True
            player1.armor_ready = False
        if player1.armor_active and pygame.time.get_ticks() - player1.armor_timer > 15000:
            player1.armor_active = False
            bought["Armor (15s)"] = False
        #draw scrolling background
        for i in range(0, tiles):
            WINDOW.blit(BG, (i * BG_width + scroll0 , 0) )
            BG_rect.x = i * BG_width + scroll0
            pygame.draw.rect(WINDOW, (53, 81, 92), BG_rect, -1)
        # set scroll
        scroll0 -= 7
        #reset scroll
        if abs(scroll0) > BG_width:
            scroll0 = 0
        # redraw window
        redraw_window()

        # settings for game playability
        if level % 10 == 0:
            wave_length = 2
        if level % 15 == 0:
            if player1.health + 50 <= 100:
                player1.health += 50
                # gain healthy sound play
                gain_healthy.play(0)

        # reset players health stuation
        if (bplayer2 == True):
            if level % 15 == 0:
                if player2.health + 50 <= 100:
                    player2.health += 50
                    # gain healthy sound play
                    gain_healthy.play(0)

            if level % 5 == 0:
                if player1.health <= 0:
                    player1.health = 100
                elif player2.health <= 0:
                    player2.health = 100


        # define movement operations for players
    # make player1 actions  ################################################3
        # take recent position in while
        recent_pos = pygame.mouse.get_pos()
        # set fighter images
        if recent_pos[1] > last_pos[1]:
            if player1.default_image < 4:
                player1.default_image = player1.default_image + 1
                player1.ship_img = pygame.image.load(r"assets\\images\\" + f'{"ship"}{player1.default_image}.png')
                player1.mask = pygame.mask.from_surface(player1.ship_img)
        if (recent_pos[1] < last_pos[1]):
            if player1.default_image > 0:
                player1.default_image = player1.default_image - 1
                player1.ship_img = pygame.image.load(r"assets\\images\\" + f'{"ship"}{player1.default_image}.png')
                player1.mask = pygame.mask.from_surface(player1.ship_img)
        else:
            player1.default_image = 2
            player1.ship_img = pygame.image.load(r'assets\\images\\ship2.png')
            player1.mask = pygame.mask.from_surface(player1.ship_img)

        # set invisible mouse
        pygame.mouse.set_visible(False)
        # set player1 with mouse location
        last_pos = pygame.mouse.get_pos()
        if player1.x <= last_pos[0] and player1.y >= last_pos[1]:  # while mouse on  "right top"  corner
            player1.x += abs(last_pos[0] - player1.x)
            player1.y -= (abs(player1.y - last_pos[1]))
        if player1.x <= last_pos[0] and player1.y <= last_pos[1]:  # while mouse on  "right bottom"  corner
            player1.x += abs(player1.x - last_pos[0])
            player1.y -= abs(player1.y - last_pos[1])
        if player1.x >= last_pos[0] and player1.y >= last_pos[1]:  # while mouse on  "left top"  corner
            player1.x -= (abs(last_pos[0] - player1.x))
            player1.y -= (abs(player1.y - last_pos[1]))
        if player1.x >= last_pos[0] and player1.y <= last_pos[1]:  # while mouse on  "left bottom"  corner
            player1.x -= (abs(player1.x - last_pos[0]))
            player1.y += abs(player1.y - last_pos[1])

        mouse_presses = pygame.mouse.get_pressed()
        if mouse_presses[0]:
            if player1.health > 0:
                player1.shoot()





        # lost operations
        if lives <= 0 or (player1.health <= 0 ):
            lost = True
            lost_count += 1
            if bplayer2 == True:
                if lives <= 0 or (player1.health <= 0 and player2.health<= 0):
                    lost = True
                    lost_count += 1
                    
        # if lost
        if lost:
            global flag
            global money
            game_over.play(0)
            if  not flag:
                money+=point/100
                flag = True
            if point > max_point:
                max_point = point
                pickle.dump(max_point, open("variableStoringFile.dat", "wb"))
            if lost_count > FPS * 3:
                run = False
            else:
                continue

        # fill enemies array
        if len(enemies) == 0:
            level += 1
            wave_length += 2
            for i in range(wave_length):
                enemy = testlib.Enemy(random.randrange(WIDTH + 200 , 1500), random.randrange(20, HEIGHT - 100), WIDTH , random.choice(["red", "blue", "green"]))
                enemies.append(enemy)
            if level % 2 == 0:
                boss = testlib.Boss(random.randrange(WIDTH + 200, 1500), random.randrange(20, HEIGHT - 100) , WIDTH)
                enemies.append(boss)

        
        
        
    #shoot operations player1 and player2
        # for player1 health and live code
        for enemy in enemies[:]:
            enemy.move(enemy_vel)
            enemy.move_lasers(laser_vel - 5, player1)

            if random.randrange(0, 2 * 60) == 1:
                enemy.shoot()

            if collide(enemy, player1) and player1.health > 0:
                if not player1.armor_active:
                    player1.health -= 10
                enemy.health -= 10
                if enemy.health <= 0:
                    # all cases deletion
                    point_gain.play(0)
                    enemies.remove(enemy)
                    explosions.append(testlib.Explosion((enemy.x -15 , enemy.y -15) , countRange , explosion_image_range ))
                    if isinstance(enemy , testlib.Enemy):
                        point = point + 100
                    elif isinstance(enemy , testlib.Boss):
                        point = point + 1000

            if enemy.x < 0 and enemy.health > 0:
                lives -= 1
                enemies.remove(enemy)

        point = player1.move_lasers(-laser_vel, enemies , point , explosions)
        # for player2 code
        if (bplayer2 == True):
            # enemy_vel = 2
            for enemy in enemies[:]:
                #enemy.move(enemy_vel)
                enemy.move_lasers(laser_vel - 5, player2)

                #if random.randrange(0, 8 * 60) == images:
                    #enemy.shoot()

                if collide(enemy, player2) and player2.health > 0:
                    player2.health -= 10
                    enemy.health -= 10
                    if enemy.health <= 0:
                        # all cases deletion
                        enemies.remove(enemy)
                        explosions.append(testlib.Explosion((enemy.x -15, enemy.y -15), countRange,explosion_image_range)) 
                        if isinstance(enemy, testlib.Enemy):
                            point = point + 100
                        elif isinstance(enemy, testlib.Boss):
                            point = point + 1000
                        point_gain.play(0)

            point = player2.move_lasers(-laser_vel, enemies , point , explosions)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                quit()




def collide(obj1, obj2): # touch operations
    offset_x = obj2.x - obj1.x
    offset_y = obj2.y - obj1.y
    return obj1.mask.overlap(obj2.mask, (offset_x, offset_y)) != None

class Background():  # background class
    def __init__(self, image_name, image=0, image_range=3, countplus = 3):
        self.images = []
        self.image = image
        self.image_range = image_range
        self.count = 0
        self.countplus = countplus
        for i in range(image_range):
            self.images.append(pygame.transform.scale((pygame.image.load(os.path.join('assets', 'images', f'{image_name}{i}.png'))), (256, 256)))
        self.screen_w, self.screen_h = WINDOW.get_size()
        self.image_w, self.image_h = self.images[self.image].get_size()
    def draw_background(self):
        for x in range(0, self.screen_w + self.count, self.image_w):
            for y in range(0, self.screen_h, self.image_h):
                WINDOW.blit(self.images[self.image], (x - self.count, y))
        if (self.count == self.screen_w-2):
            self.count = 0
        elif(self.count % 72 == 0):
            self.image = (self.image + 1)%self.image_range
        self.count += self.countplus

def shop():
    global money
    global bought
    pygame.font.init()
    shop_font = pygame.font.SysFont("comicsans", 50)
    items = [
        {"name": "Armor (15s)", "price": 40},
        {"name": "Triple Shot", "price": 60}
    ]
    run = True
    while run:
        WINDOW.fill((20, 20, 40))
        title = shop_font.render("SHOP", 1, (255, 255, 255))
        titl = shop_font.render("Money$: ", 1, (255, 255, 255))
        txtBack = shop_font.render("Back", 1, (255, 255, 255))
        txtBackRect = txtBack.get_rect()
        txtBackRect.topleft = (50, HEIGHT - 100)
        txtmoney = shop_font.render(str(money), 1, (255, 255, 255))
        txtmonRect = txtmoney.get_rect()
        txtmonRect.topleft = (1000, HEIGHT - 450)
        WINDOW.blit(title, (WIDTH//2 - title.get_width()//2, 30))
        WINDOW.blit(titl, (800, 188))
        WINDOW.blit(txtmoney, txtmonRect)
        WINDOW.blit(txtBack, txtBackRect)
        buttons = []
        for i, item in enumerate(items):
            text = shop_font.render(f"{item['name']} - ${item['price']}", 1, (255, 255, 255))
            rect = pygame.Rect(100, 120 + i * 80, 500, 60)
            buttons.append([rect, item])   
            pygame.draw.rect(WINDOW, (60,60,100), rect)
            WINDOW.blit(text, (110, 110 + i * 80))

        pygame.display.update()
        for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    quit()
                if event.type == pygame.MOUSEBUTTONDOWN: 
                    for i in buttons:      
                        if i[0].collidepoint(pygame.mouse.get_pos()):
                            if money >= i[1]['price'] and not bought[i[1]['name']]:
                                bought[i[1]['name']] = True
                                money -= i[1]['price'] 
                        if event.type == pygame.MOUSEBUTTONDOWN and txtBackRect.collidepoint(pygame.mouse.get_pos()):
                            return bought["Armor (15s)"], bought["Triple Shot"]



def menu():

    menu_bg = Background('bg', countplus=0)
    universe = []
    for i in range(120):
        universe.append(pygame.transform.scale(pygame.image.load(rf'assets\images\universe\frame_{i:03d}_delay-0.09s.png'), (600, 600)))
    co = 0

    txt = main_font.render('SPACE DEFENDER', True, (255,255,255))
    txtRect = txt.get_rect()
    txtRect.center  = ((WIDTH / 2) , 100)

    txtBegin = main_font.render('BEGIN', True, (255,255,255))
    txtBeginRect = txtBegin.get_rect()
    txtBeginRect.center = (1050, 550)
    
    txtShop = main_font.render('SHOP', True, (255,255,255))
    txtShopRect = txtShop.get_rect()
    txtShopRect.center = (1050, 470)
    


    bMusic = False
    txt_Music = lost_font.render('Music ? : ', True, (255, 255, 255))
    txt_Music_Rect = txt_Music.get_rect()
    txt_Music_Rect.center = (250, HEIGHT - 50)

    bplayer1 = True



    while True:
        pygame.mouse.set_visible(True)
        menu_bg.draw_background()
        WINDOW.blit(universe[co], (20, 20))
        WINDOW.blit(txt, txtRect)
        WINDOW.blit(txtShop, txtShopRect)
        WINDOW.blit(txtBegin, txtBeginRect)
        WINDOW.blit(txt_Music, txt_Music_Rect)


        if bMusic == True:
            image = pygame.image.load(rf'assets\images\tick.jpg')
            image = pygame.transform.scale(image, (40, 40))
            WINDOW.blit(image, (450, HEIGHT - 70))

        co = (co + 1) % 120
        clock.tick(20)
        pygame.display.update()
        for event in pygame.event.get():
            if event.type == pygame.MOUSEBUTTONDOWN:
                bplayer1 = True
                bplayer2 = False
            if event.type == pygame.MOUSEBUTTONDOWN and txt_Music_Rect.collidepoint(pygame.mouse.get_pos()):
                if bMusic == False : bMusic = True
                elif bMusic == True : bMusic = False
            if event.type == pygame.MOUSEBUTTONDOWN and txtShopRect.collidepoint(pygame.mouse.get_pos()):
                armor_bought, triple_bought = shop()  # GIVE 0 BEFORE GAME!!!!
            if event.type == pygame.MOUSEBUTTONDOWN and txtBeginRect.collidepoint(pygame.mouse.get_pos()):
                main(bplayer2 , bMusic)
                global flag
                flag = False
            if event.type == pygame.QUIT:
                quit()

menu()
