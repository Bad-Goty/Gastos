package com.example.gastos.roomdb

import kotlinx.coroutines.flow.Flow


class SaldoActualRepository(private val saldoActualDao: SaldoActualDao) {


    fun getAllSaldos(): Flow<List<SaldoActual>> = saldoActualDao.getAllSaldos()

    suspend fun getUltimoSaldo(): SaldoActual? = saldoActualDao.getUltimoSaldo()

    suspend fun insertSaldo(saldo: SaldoActual) = saldoActualDao.insertSaldo(saldo)

    suspend fun updateSaldo(saldo: SaldoActual) = saldoActualDao.updateSaldo(saldo)

    suspend fun deleteSaldo(saldo: SaldoActual) = saldoActualDao.deleteSaldo(saldo)

    suspend fun getSaldoById(id: Int) = saldoActualDao.getSaldoById(id)

}