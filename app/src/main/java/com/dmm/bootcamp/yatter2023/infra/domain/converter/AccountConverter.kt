package com.dmm.bootcamp.yatter2023.infra.domain.converter

import com.dmm.bootcamp.yatter2023.BuildConfig
import com.dmm.bootcamp.yatter2023.domain.model.Account
import com.dmm.bootcamp.yatter2023.domain.model.AccountId
import com.dmm.bootcamp.yatter2023.domain.model.Username
import com.dmm.bootcamp.yatter2023.infra.api.json.AccountJson
import com.dmm.bootcamp.yatter2023.infra.domain.model.AccountImpl
import java.net.URL

object AccountConverter {
  fun convertToDomainModel(
    jsonList: List<AccountJson>
  ): List<Account> = jsonList.map { convertToDomainModel(it) }

  fun convertToDomainModel(json: AccountJson): Account = AccountImpl(
    id = AccountId(json.id),
    username = Username(json.username),
    displayName = json.displayName,
    note = json.note,
    avatar = json.avatar?.let { URL(it) },
    header = json.header?.let { URL(it) },
    followingCount = json.followingCount ?: 0,
    followerCount = json.followersCount ?: 0,
  )
}