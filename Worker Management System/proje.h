
#ifndef PROJE_H
#define PROJE_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/**
* @file header code
* @description functions are here
* @assignment first assignment
* @date 22.12.2024
* @author ILKHAMBEK i.mavlyudov@stu.fsm.edu.tr
*/

typedef struct
{
    char *calisanAd;    // Dinamik allocate edilecek (örn. 30 char)
    char *calisanSoyad; // Dinamik allocate edilecek (örn. 30 char)
    unsigned short int birimKodu;
    float maas;
    int girisYili;
} Calisan;

typedef struct
{
    char *birimAdi; // Dinamik allocate edilecek (örn. 30 char)
    unsigned short int birimKodu;
    Calisan *birimIciCalisanlar; // 30 elemanlık dizi
    int calisanSayisi;
} Birim;




Birim birimOkuKullanici();


Calisan calisanOkuKullanici();


void birimEkle(Birim **birimler, int *birimSayisi, Birim yeniBirim);


void calisanEkle(Calisan **calisanlar, int *calisanSayisi, Calisan yeniCalisan);


void birimDizisiniDosyayaYaz(const char *dosyaAdi, Birim *birimler, int birimSayisi);


void calisanDizisiniDosyayaYaz(const char *dosyaAdi, Calisan *calisanlar, int calisanSayisi);


void birimDizisiniDosyadanOku(const char *dosyaAdi, Birim **birimler, int *birimSayisi);


void calisanDizisiniDosyadanOku(const char *dosyaAdi, Calisan **calisanlar, int *calisanSayisi);


float birimMaasOrtalamasi(Birim birim);


void birimMinMaxMaasYaz(const char *dosyaAdi, Birim *birimler, int birimSayisi);


void tumBirimVeCalisanlariDosyayaYaz(const char *dosyaAdi, Birim *birimler, int birimSayisi, Calisan *calisanlar, int calisanSayisi);

#endif
