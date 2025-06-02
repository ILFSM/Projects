#include "Proje.h"
#include <stdio.h>
#include <stdlib.h>
/**
* @file main code
* @description functions are here
* @assignment first assignment
* @date 22.12.2024
* @author ILKHAMBEK i.mavlyudov@stu.fsm.edu.tr
*/

int main(int argc, char *argv[])
{
    if (argc < 5)
    {
        printf("Kullanim: %s birimDosyasi calisanDosyasi minmaxDosyasi tumBilgiDosyasi\n", argv[0]);
        return 1;
    }

    const char *birimDosyasi = "birimler.txt";
    const char *calisanDosyasi = "Calisanlar.txt";
    const char *minmaxDosyasi = "output_minmax.txt";
    const char *tumBilgiDosyasi = "output_all.txt";



    Birim *birimler = NULL;
    int birimSayisi = 0;
    Calisan *calisanlar = NULL;
    int calisanSayisi = 0;


    birimDizisiniDosyadanOku(birimDosyasi, &birimler, &birimSayisi);
    calisanDizisiniDosyadanOku(calisanDosyasi, &calisanlar, &calisanSayisi);


    for (int i = 0; i < calisanSayisi; i++)
    {

        for (int j = 0; j < birimSayisi; j++)
        {
            if (birimler[j].birimKodu == calisanlar[i].birimKodu)
            {
                if (birimler[j].calisanSayisi < 30)
                {
                    birimler[j].birimIciCalisanlar[birimler[j].calisanSayisi] = calisanlar[i];
                    birimler[j].calisanSayisi++;
                }
                else
                {
                    printf("Uyari: %s biriminde calisan kapasitesi dolu!\n", birimler[j].birimAdi);
                }
                break;
            }
        }
    }


    printf("Yeni bir birim eklemek ister misiniz? (E/H): ");
    char secim;
    scanf(" %c", &secim);
    int tmp;
    while ((tmp = getchar()) != '\n' && tmp != EOF)
        ;
    if (secim == 'E' || secim == 'e')
    {
        Birim nb = birimOkuKullanici();
        birimEkle(&birimler, &birimSayisi, nb);
    }

    printf("Yeni bir calisan eklemek ister misiniz? (E/H): ");
    scanf(" %c", &secim);
    while ((tmp = getchar()) != '\n' && tmp != EOF)
        ;
    if (secim == 'E' || secim == 'e')
    {
        Calisan nc = calisanOkuKullanici();

        calisanEkle(&calisanlar, &calisanSayisi, nc);

        for (int j = 0; j < birimSayisi; j++)
        {
            if (birimler[j].birimKodu == nc.birimKodu)
            {
                if (birimler[j].calisanSayisi < 30)
                {
                    birimler[j].birimIciCalisanlar[birimler[j].calisanSayisi] = nc;
                    birimler[j].calisanSayisi++;
                }
                else
                {
                    printf("Uyari: %s biriminde calisan kapasitesi dolu!\n", birimler[j].birimAdi);
                }
                break;
            }
        }
    }
    printf("%f", birimMaasOrtalamasi( *birimler));

    birimMinMaxMaasYaz(minmaxDosyasi, birimler, birimSayisi);

    birimMaasOrtalamasi(*birimler);
    tumBirimVeCalisanlariDosyayaYaz(tumBilgiDosyasi, birimler, birimSayisi, calisanlar, calisanSayisi);


    birimDizisiniDosyayaYaz(birimDosyasi, birimler, birimSayisi);
    calisanDizisiniDosyayaYaz(calisanDosyasi, calisanlar, calisanSayisi);


    for (int i = 0; i < birimSayisi; i++)
    {
        for (int j = 0; j < birimler[i].calisanSayisi; j++)
        {
            free(birimler[i].birimIciCalisanlar[j].calisanAd);
            free(birimler[i].birimIciCalisanlar[j].calisanSoyad);
        }
        free(birimler[i].birimAdi);
        free(birimler[i].birimIciCalisanlar);
    }
    free(birimler);

    for (int i = 0; i < calisanSayisi; i++)
    {

        free(calisanlar[i].calisanAd);
        free(calisanlar[i].calisanSoyad);
    }
    free(calisanlar);

    return 0;
}
