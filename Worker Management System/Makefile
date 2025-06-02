CC = gcc
CFLAGS = -Wall -O2

all: program

program: main.o Proje.o
	$(CC) $(CFLAGS) -o program main.o Proje.o

main.o: main.c Proje.h
	$(CC) $(CFLAGS) -c main.c

Proje.o: Proje.c Proje.h
	$(CC) $(CFLAGS) -c Proje.c

clean:
	rm -f *.o program
