/**
* @file с code
* @description functions are here
* @assignment first assignment
* @date 22.12.2024
* @author ILKHAMBEK i.mavlyudov@stu.fsm.edu.tr
*/

#include "Proje.h"


Birim birimOkuKullanici()
{
    Birim b;
    b.birimAdi = (char *)malloc(sizeof(char) * 30);
    b.birimIciCalisanlar = (Calisan *)malloc(sizeof(Calisan) * 30);
    b.calisanSayisi = 0;

    printf("Birim Adi: ");
    fgets(b.birimAdi, 30, stdin);
    b.birimAdi[strcspn(b.birimAdi, "\n")] = '\0';

    printf("Birim Kodu (unsigned short): ");
    unsigned int temp;
    scanf("%u", &temp);
    b.birimKodu = (unsigned short int)temp;


    int c;
    while ((c = getchar()) != '\n' && c != EOF)
        ;

    return b;
}


Calisan calisanOkuKullanici()
{
    Calisan c;
    c.calisanAd = (char *)malloc(sizeof(char) * 30);
    c.calisanSoyad = (char *)malloc(sizeof(char) * 30);

    printf("Calisan Ad: ");
    fgets(c.calisanAd, 30, stdin);
    c.calisanAd[strcspn(c.calisanAd, "\n")] = '\0';

    printf("Calisan Soyad: ");
    fgets(c.calisanSoyad, 30, stdin);
    c.calisanSoyad[strcspn(c.calisanSoyad, "\n")] = '\0';

    printf("Birim Kodu: ");
     int bk;
    scanf("%d", &bk);
    c.birimKodu = bk;

    printf("Maas: ");
    scanf("%f", &c.maas);

    printf("Giris Yili: ");
    scanf("%d", &c.girisYili);

    int tmp;
    while ((tmp = getchar()) != '\n' && tmp != EOF)
        ;

    return c;
}


void birimEkle(Birim **birimler, int *birimSayisi, Birim yeniBirim)
{
    *birimler = (Birim *)realloc(*birimler, sizeof(Birim) * ((*birimSayisi) + 1));
    (*birimler)[*birimSayisi] = yeniBirim;
    (*birimSayisi)++;
}


void calisanEkle(Calisan **calisanlar, int *calisanSayisi, Calisan yeniCalisan)
{
    *calisanlar = (Calisan *)realloc(*calisanlar, sizeof(Calisan) * ((*calisanSayisi) + 1));
    (*calisanlar)[*calisanSayisi] = yeniCalisan;
    (*calisanSayisi)++;
}


void birimDizisiniDosyayaYaz(const char *dosyaAdi, Birim *birimler, int birimSayisi)
{
    FILE *f = fopen(dosyaAdi, "w");
    if (!f)
    {
        perror("Birim dosyasi acilamadi");
        return;
    }

    for (int i = 0; i < birimSayisi; i++)
    {
        fprintf(f, "%s %hu\n", birimler[i].birimAdi, birimler[i].birimKodu);
    }
    fclose(f);
}


void calisanDizisiniDosyayaYaz(const char *dosyaAdi, Calisan *calisanlar, int calisanSayisi)
{
    FILE *f = fopen(dosyaAdi, "w");
    if (!f)
    {
        perror("Calisan dosyasi acilamadi");
        return;
    }

    for (int i = 0; i < calisanSayisi; i++)
    {
        fprintf(f, "%s %s %hu %.2f %d\n",
                calisanlar[i].calisanAd,
                calisanlar[i].calisanSoyad,
                calisanlar[i].birimKodu,
                calisanlar[i].maas,
                calisanlar[i].girisYili);
    }
    fclose(f);
}

void birimDizisiniDosyadanOku(const char *dosyaAdi, Birim **birimler, int *birimSayisi)
{
    FILE *f = fopen(dosyaAdi, "r");
    if (!f)
    {

        return;
    }
    char buffer[100];
    unsigned short int bk;

    while (!feof(f))
    {
        if (fscanf(f, "%s %hu", buffer, &bk) == 2)
        {
            Birim b;
            b.birimAdi = (char *)malloc(sizeof(char) * 30);
            strncpy(b.birimAdi, buffer, 29);
            b.birimAdi[29] = '\0';
            b.birimKodu = bk;
            b.birimIciCalisanlar = (Calisan *)malloc(sizeof(Calisan) * 30);
            b.calisanSayisi = 0;
            birimEkle(birimler, birimSayisi, b);
        }
    }

    fclose(f);
}


void calisanDizisiniDosyadanOku(const char *dosyaAdi, Calisan **calisanlar, int *calisanSayisi)
{
    FILE *f = fopen(dosyaAdi, "r");
    if (!f)
    {

        return;
    }
    char ad[50], soyad[50];
    unsigned short int bk;
    float maas;
    int y;

    while (!feof(f))
    {
        if (fscanf(f, "%s %s %hu %f %d", ad, soyad, &bk, &maas, &y) == 5)
        {
            Calisan c;
            c.calisanAd = (char *)malloc(sizeof(char) * 30);
            c.calisanSoyad = (char *)malloc(sizeof(char) * 30);
            strncpy(c.calisanAd, ad, 29);
            c.calisanAd[29] = '\0';
            strncpy(c.calisanSoyad, soyad, 29);
            c.calisanSoyad[29] = '\0';
            c.birimKodu = bk;
            c.maas = maas;
            c.girisYili = y;
            calisanEkle(calisanlar, calisanSayisi, c);
        }
    }

    fclose(f);
}


float birimMaasOrtalamasi(Birim birim)
{
    if (birim.calisanSayisi == 0)
        return 0.0;
    float toplam = 0.0;
    for (int i = 0; i < birim.calisanSayisi; i++)
    {
        toplam += birim.birimIciCalisanlar[i].maas;
    }
    return toplam / birim.calisanSayisi;
}


void birimMinMaxMaasYaz(const char *dosyaAdi, Birim *birimler, int birimSayisi)
{
    FILE *f = fopen(dosyaAdi, "w");
    if (!f)
    {
        perror("Dosya acilamadi");
        return;
    }
    for (int i = 0; i < birimSayisi; i++)
    {
        if (birimler[i].calisanSayisi == 0)
        {
            fprintf(f, "Birim: %s(%hu), calisan yok.\n", birimler[i].birimAdi, birimler[i].birimKodu);
            continue;
        }
        float minM = birimler[i].birimIciCalisanlar[0].maas;
        float maxM = birimler[i].birimIciCalisanlar[0].maas;
        int minIndex = 0;
        int maxIndex = 0;
        for (int j = 1; j < birimler[i].calisanSayisi; j++)
        {
            if (birimler[i].birimIciCalisanlar[j].maas < minM)
            {
                minM = birimler[i].birimIciCalisanlar[j].maas;
                minIndex = j;
            }
            if (birimler[i].birimIciCalisanlar[j].maas > maxM)
            {
                maxM = birimler[i].birimIciCalisanlar[j].maas;
                maxIndex = j;
            }
        }
        fprintf(f, "Birim: %s(%hu)\n", birimler[i].birimAdi, birimler[i].birimKodu);
        fprintf(f, "Min Maasli Calisan: %s %s %.2f\n", birimler[i].birimIciCalisanlar[minIndex].calisanAd, birimler[i].birimIciCalisanlar[minIndex].calisanSoyad, minM);
        fprintf(f, "Max Maasli Calisan: %s %s %.2f\n", birimler[i].birimIciCalisanlar[maxIndex].calisanAd, birimler[i].birimIciCalisanlar[maxIndex].calisanSoyad, maxM);
    }
    fclose(f);
}

// 11. Tum birim ve calisanlari dosyaya yaz
void tumBirimVeCalisanlariDosyayaYaz(const char *dosyaAdi, Birim *birimler, int birimSayisi, Calisan *calisanlar, int calisanSayisi)
{
    FILE *f = fopen(dosyaAdi, "w");
    if (!f)
    {
        perror("Dosya acilamadi");
        return;
    }

    fprintf(f, "=== Birimler ===\n");
    for (int i = 0; i < birimSayisi; i++)
    {
        fprintf(f, "Birim: %s(%hu), CalisanSayisi:%d\n", birimler[i].birimAdi, birimler[i].birimKodu, birimler[i].calisanSayisi);
    }

    fprintf(f, "\n=== Calisanlar ===\n");
    for (int i = 0; i < calisanSayisi; i++)
    {
        fprintf(f, "%s %s Birim:%hu Maas:%.2f GirisYili:%d\n",
                calisanlar[i].calisanAd, calisanlar[i].calisanSoyad, calisanlar[i].birimKodu, calisanlar[i].maas, calisanlar[i].girisYili);
    }

    fclose(f);
}
