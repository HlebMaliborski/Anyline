package com.devloper.squad.base.common

interface Mapper<in T, out K> {

    fun map(data: T): K
}

interface MapperWithTwoParams<in T, in J, out K> {

    fun map(data1: T, data2: J): K
}