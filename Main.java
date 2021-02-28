package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[][] chromosom = new int[6][5];
        Random generator = new Random();
        Double[] suma = new Double[6];
        Scanner odczyt = new Scanner(System.in);
        int[] dane = new int[6];
        char znak = 97;
        Double[] funkcja = new Double[6];
        Double pierwszaFunkcja = 0.0;
        int mutacja;
        int krzyzowanie;
        Double[] udzial = new Double[6];
        double ruletka;
        int lokus;
        int pk;
        int pm;
        int koniec = 0;
        double maksimum = 0.0;
        int[][] temp = new int[3][5];
        int licznik = 0;
        double maks;
        int powtorzenie;

        for (int i = 0; i < 6; i++) {
            suma[i] = (double) 0;
        }
        System.out.println("Podaj współczynniki funkcji F(x)= ax^3+bx^2+cx+d");
        for (int i = 0; i < 4; i++) {
            System.out.print(znak + " = ");
            dane[i] = odczyt.nextInt();
            ++znak;
        }
        System.out.println("Podaj wspólczynik krzyżowania od 0 do 100:");
        krzyzowanie = odczyt.nextInt();
        System.out.println("Podaj wspólczynik mutacji od 0 do 100:");
        mutacja = odczyt.nextInt();
        System.out.println("Podaj ilość wystąpień najwyższego wyniku:");
        powtorzenie = odczyt.nextInt();


        for (int j = 0; j < 6; j++) {
            System.out.print("Ch" + (j + 1) + ": ");
            for (int i = 0; i < 5; i++) {
                chromosom[j][i] = ((generator.nextInt(2)));
                if (chromosom[j][i] == 1) {
                    suma[j] = suma[j] + Math.pow(2, 5 - 1 - i);
                }
                System.out.print(chromosom[j][i]);
            }
            System.out.println("=" + suma[j]);
        }

        System.out.println("Postac funkcji F(x)=" + dane[0] + "x^3+" + dane[1] + "x^2+" + dane[2] + "x+" + dane[3]);
        System.out.println("Funkcja przystosowania:");

        for (int i = 0; i < 6; i++) {
            funkcja[i] = dane[0] * (suma[i] * suma[i] * suma[i]) + dane[1] * (suma[i] * suma[i]) + dane[2] * suma[i] + dane[3];
            System.out.println("Ch" + (i + 1) + "=" + funkcja[i]);
            pierwszaFunkcja = pierwszaFunkcja + funkcja[i];
        }
        System.out.println("Suma wartości wszystkich funkcji przystosowania: " + pierwszaFunkcja);
        System.out.println("Procentowy udział każdego z chromosomów: ");
        for (int i = 0; i < 6; i++) {
            udzial[i] = ((funkcja[i] / pierwszaFunkcja) * 100);
            System.out.println("Ch" + (i + 1) + "=" + udzial[i]);
        }

        for (int z = 0; koniec < powtorzenie; z++) {
            for (int j = 0; j < 6; j++) {
                ruletka = (generator.nextDouble() * 100);
                for (int i = 0; i < 5; i++) {
                    if (ruletka > 0 && ruletka < udzial[0]) {
                        chromosom[j][i] = chromosom[0][i];
                    } else if (ruletka > udzial[0] && ruletka < (udzial[0] + udzial[1])) {
                        chromosom[j][i] = chromosom[1][i];
                    } else if (ruletka > (udzial[0] + udzial[1]) && ruletka < udzial[0] + udzial[1] + udzial[2]) {
                        chromosom[j][i] = chromosom[2][i];
                    } else if (ruletka > (udzial[0] + udzial[1] + udzial[2]) && ruletka < (udzial[0] + udzial[1] + udzial[2] + udzial[3])) {
                        chromosom[j][i] = chromosom[3][i];
                    } else if (ruletka > (udzial[0] + udzial[1] + udzial[2] + udzial[3]) && ruletka < (udzial[0] + udzial[1] + udzial[2] + udzial[3] + udzial[4])) {
                        chromosom[j][i] = chromosom[4][i];
                    } else if (ruletka > (udzial[0] + udzial[1] + udzial[2] + udzial[3] + udzial[4]) && ruletka < 100) {
                        chromosom[j][i] = chromosom[5][i];
                    }
                }
            }

            lokus = (generator.nextInt(4));
            pk = (generator.nextInt(100));
            for (int i = lokus; i < 5; i++) {
                if (pk < krzyzowanie) {
                    temp[0][i] = chromosom[0][i];
                    chromosom[0][i] = chromosom[1][i];
                    chromosom[1][i] = temp[0][i];
                }
            }
            lokus = (generator.nextInt(4));
            pk = (generator.nextInt(100));
            for (int i = lokus; i < 5; i++) {
                if (pk < krzyzowanie) {
                    temp[1][i] = chromosom[2][i];
                    chromosom[2][i] = chromosom[3][i];
                    chromosom[3][i] = temp[1][i];
                }
            }
            lokus = (generator.nextInt(4));
            pk = (generator.nextInt(100));
            for (int i = lokus; i < 5; i++) {
                if (pk < krzyzowanie) {
                    temp[2][i] = chromosom[4][i];
                    chromosom[4][i] = chromosom[5][i];
                    chromosom[5][i] = temp[2][i];
                }
            }
            lokus = (generator.nextInt(5));
            pm = (generator.nextInt(100));
            for (int i = 0; i < 1; i++) {
                if (pm < mutacja) {
                    if (chromosom[0][lokus] == 0) {
                        chromosom[0][lokus] = 1;
                    } else if
                    (chromosom[0][lokus] == 1) {
                        chromosom[0][lokus] = 0;
                    }
                }
            }
            lokus = (generator.nextInt(5));
            pm = (generator.nextInt(100));
            for (int i = 0; i < 1; i++) {
                if (pm < mutacja) {
                    if (chromosom[1][lokus] == 0) {
                        chromosom[1][lokus] = 1;
                    } else if
                    (chromosom[1][lokus] == 1) {
                        chromosom[1][lokus] = 0;
                    }
                }
            }
            lokus = (generator.nextInt(5));
            pm = (generator.nextInt(100));
            for (int i = 0; i < 1; i++) {
                if (pm < mutacja) {
                    if (chromosom[2][lokus] == 0) {
                        chromosom[2][lokus] = 1;
                    } else if
                    (chromosom[2][lokus] == 1) {
                        chromosom[2][lokus] = 0;
                    }
                }
            }
            lokus = (generator.nextInt(5));
            pm = (generator.nextInt(100));
            for (int i = 0; i < 1; i++) {
                if (pm < mutacja) {
                    if (chromosom[3][lokus] == 0) {
                        chromosom[3][lokus] = 1;
                    } else if
                    (chromosom[3][lokus] == 1) {
                        chromosom[3][lokus] = 0;
                    }
                }
            }
            lokus = (generator.nextInt(5));
            pm = (generator.nextInt(100));
            for (int i = 0; i < 1; i++) {
                if (pm < mutacja) {
                    if (chromosom[4][lokus] == 0) {
                        chromosom[4][lokus] = 1;
                    } else if
                    (chromosom[4][lokus] == 1) {
                        chromosom[4][lokus] = 0;
                    }
                }
            }
            lokus = (generator.nextInt(5));
            pm = (generator.nextInt(100));
            for (int i = 0; i < 1; i++) {
                if (pm < mutacja) {
                    if (chromosom[5][lokus] == 0) {
                        chromosom[5][lokus] = 1;
                    } else if
                    (chromosom[5][lokus] == 1) {
                        chromosom[5][lokus] = 0;
                    }
                }
            }
            System.out.println("Chromosomy po losowaniu, krzyżowaniu, mutacji oraz ich wartość: ");
            for (int j = 0; j < 6; j++) {
                System.out.print("Ch" + (j + 1) + ": ");
                suma[j] = 0.0;
                for (int i = 0; i < 5; i++) {
                    if (chromosom[j][i] == 1) {
                        suma[j] = suma[j] + Math.pow(2, 5 - 1 - i);
                    }
                    System.out.print(chromosom[j][i]);
                }
                System.out.println("=" + suma[j]);
            }

            pierwszaFunkcja = 0.0;

            System.out.println("Funkcja przystosowania:");
            for (int i = 0; i < 6; i++) {
                funkcja[i] = 0.0;
                funkcja[i] = dane[0] * (suma[i] * suma[i] * suma[i]) + dane[1] * (suma[i] * suma[i]) + dane[2] * suma[i] + dane[3];
                System.out.println("Ch" + (i + 1) + "=" + funkcja[i]);
                pierwszaFunkcja = pierwszaFunkcja + funkcja[i];
            }
            maks = pierwszaFunkcja;
            if
            (maksimum < maks) {
                maksimum = maks;
                koniec = 0;
            } else if (maksimum == maks) {
                koniec = koniec + 1;
            }
            licznik = licznik + 1;
            System.out.println("Procentowy udział każdego z chromosomów: ");
            for (int i = 0; i < 6; i++) {
                udzial[i] = ((funkcja[i] / pierwszaFunkcja) * 100);
                System.out.println("Ch" + (i + 1) + "=" + udzial[i]);
            }
                System.out.println("Suma wartości najlepszych funkcji przystosowania: " + pierwszaFunkcja);
                System.out.println("Wykonano " + licznik + " iteacji");
                System.out.println();
            }
        }
    }




