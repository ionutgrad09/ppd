
#include "pch.h"
#include <iostream>
#include <stdlib.h>
#include <time.h>
#include <chrono>
#include <vector>
#include "Threadpool.h"

typedef std::chrono::high_resolution_clock Clock;
using namespace std;

#define M 10
#define N 10
#define T 10

int matrix1[M][N], matrix2[M][N];

//result matrices
int normalAdd[M][N];
int threadsAdd[M][N];
int normalProd[M][N];
int threadsProd[M][N];

void initializeMatrices() {
	srand(time(NULL));
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			matrix1[i][j] = rand() % 10 + 1;
		}
	}
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			matrix2[i][j] = rand() % 10 + 1;
		}
	}
}

void printMatrix(int m[M][N]) {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			cout << m[i][j] << " ";
		}
		cout << endl;
	}
	cout << endl;
}

void addNormal() {
	for (int i = 0; i < M; i++)
		for (int j = 0; j < N; j++)
			normalAdd[i][j] = matrix1[i][j] + matrix2[i][j];
}

void addThreads(int id)
{

	for (int i = id; i < M; i += T)
	{
		for (int j = 0; j < N; j++)
		{
			threadsAdd[i][j] = matrix1[i][j] + matrix2[i][j];
		}
	}
}

void prodNormal() {
	for (int i = 0; i < M; i++)
	{
		for (int j = 0; j < N; j++)
		{
			int resInt = 0;
			for (int idx = 0; idx < N; idx++)
			{
				resInt += matrix1[i][idx] * matrix2[idx][j];
			}
			normalProd[i][j] = resInt;
		}
	}
}

void prodThreads(int i)
{
	int r;
	for (int k = i; k < M; k += T)
		for (int j = 0; j < N; j++)
		{
			r = 0;

			for (int id = 0; id < N; id++)
			{
				r += matrix1[i][id] * matrix2[id][j];
			}
			threadsProd[k][j] = r;
		}
}

void Add() {

	addNormal();
	cout << "Normal add:" << endl;
	printMatrix(normalAdd);

	ThreadPool poolT(T);
	vector<future<void>> addThreadFutures;
	for (int i = 0; i < T; ++i) {
		addThreadFutures.emplace_back(poolT.enqueue([i] {addThreads(i); }));
	}

	for (auto && res : addThreadFutures) {
		res.get();
	}

	cout << "Thread add: " << endl;
	printMatrix(threadsAdd);
}

void Prod() {

	prodNormal();
	cout << "Prod normal:" << endl;
	printMatrix(normalProd);

	vector<future<void>> prodThreadFutures;
	ThreadPool poolT(T);
	for (int i = 0; i < T; i++) {
		prodThreadFutures.emplace_back(poolT.enqueue([i] {prodThreads(i); }));
	}

	for (auto && res : prodThreadFutures) {
		res.get();
	}

	cout << "Thread prod:" << endl;
	printMatrix(threadsProd);
}



int main(int argc, const char * argv[]) {
	initializeMatrices();
	cout << "Generated matrices :" << endl;
	printMatrix(matrix1);
	printMatrix(matrix2);
	
	cout << "ADD :" << endl;
	Add();

	cout << "PROD :" << endl;
	Prod();

	return 0;
}