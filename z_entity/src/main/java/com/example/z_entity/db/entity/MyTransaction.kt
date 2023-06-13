package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.TransactionData
import com.example.z_entity.db.remote_models.TransactionRemote

@Entity(tableName = "transactions")
data class MyTransaction(
    @PrimaryKey
    var id: String,
    var type: Int = 0,
    var fromId: String,
    var toId: String,
    var currencyId: String,
    var amount: Double,
    var currencyFrom: String = "",
    var currencyTo: String = "",
    var date: Long,
    var comment: String = "",
    var uploaded: Boolean = false,

    var isFromPocket: Boolean = false,
    var isToPocket: Boolean = false,
    var rate: Double = 1.0,
    var rateFrom: Double = 1.0,
    var rateTo: Double = 1.0,
    var balance: Double = 0.0
) {
    fun toTransactionRemote() = TransactionRemote(
        id,
        type,
        fromId,
        toId,
        currencyId,
        amount,
        currencyFrom,
        currencyTo,
        date,
        comment,
        isFromPocket,
        isToPocket,
        rate,
        rateFrom,
        rateTo,
        balance
    )
}

fun MyTransaction.toTransactionData() = TransactionData(
    id,
    type,
    fromId,
    toId,
    currencyId,
    amount,
    currencyFrom,
    currencyTo,
    date,
    comment,
    isFromPocket,
    isToPocket,
    rate,
    rateFrom,
    rateTo,
    balance
)

fun TransactionData.toMyTransaction() = MyTransaction(
    id,
    type,
    fromId,
    toId,
    currencyId,
    amount,
    currencyFrom,
    currencyTo,
    date,
    comment,
    isFromPocket = isFromPocket,
    isToPocket = isToPocket,
    rate = rate,
    rateFrom = rateFrom,
    rateTo = rateTo,
    balance = balance
)
