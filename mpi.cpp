#include <iostream>
#include <time.h>
#include <cmath>
#include <random>
#include <stdio.h>
#include <mpi.h>

#define MY_TAG 1

using namespace std;

double delta = 0.0001;
default_random_engine eng{static_cast<long unsigned int>(time(0))};
uniform_real_distribution<double> distr_x(X1, X2);
uniform_real_distribution<double> distr_y(Y1, Y2);

double calculateValue(double x);
double calculateDistance(double x1, double x2);

int main(int argc, char *argv[])
{
    int rank, size;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    // komunikat
    if(rank == 0){
        cout << "delta " << delta  << '\n';
        cout << "Ilosc powtorzen: " << size << '\n';
    }

    double sum = 0.0;
    for (double i = rank; i < size; i += delta) {
        sum += calculateDistance(i, i + delta);
    }

    // synchronizacja, zebranie i zsumowanie danych
    double avg = 0.0;
    MPI_Reduce(&sum,
               &avg,
               1,
               MPI_DOUBLE,
               MPI_SUM,
               0,
               MPI_COMM_WORLD);
    if (rank == 0)
    {
        printf("Suma wynosi: %lf", sum);
    }
    MPI_Finalize();
    return 0;
}

double calculateValue(double x)
{
    double firstPart = sin(x*x) * x;
    double secondPart = 1/3 * exp(x);
    double thirdPart = cos(log(x));
    return firstPart + secondPart + thirdPart;
}

double calculateDistance(double x1, double x2)
{
    double y1 = calculateValue(x1);
    double y2 = calculateValue(x2);
    return sqrt((y2 - y1) * (y2 - y1) + (x2 - x1)(x2 - x1));
}
