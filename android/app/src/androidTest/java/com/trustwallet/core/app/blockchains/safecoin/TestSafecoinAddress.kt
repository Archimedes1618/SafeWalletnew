package com.trustwallet.core.app.blockchains.safecoin

import org.junit.Assert.assertEquals
import org.junit.Test
import wallet.core.jni.*

class TestSafecoinAddress {

    init {
        System.loadLibrary("TrustWalletCore")
    }

    private val expectedAddressString = "7v91N7iZ9mNicL8WfG6cgSCKyRXydQjLh6UYBWwm6y1Q"

    @Test
    fun testAddressFromPrivateKey() {
        val key = PrivateKey(Base58.decodeNoCheck("A7psj2GW7ZMdY4E5hJq14KMeYg7HFjULSsWSrTXZLvYr"))
        val pubkey = key.publicKeyEd25519
        val address = AnyAddress(pubkey, CoinType.SAFECOIN)

        assertEquals(address.description(), expectedAddressString)
    }

    @Test
    fun testAddressFromPublicKey() {
        val pubkey = PublicKey(Base58.decodeNoCheck(expectedAddressString), PublicKeyType.ED25519)
        val address = AnyAddress(pubkey, CoinType.SAFECOIN)

        assertEquals(address.description(), expectedAddressString)
    }

    @Test
    fun testAddressFromString() {
        val addressString = expectedAddressString
        val address = AnyAddress(addressString, CoinType.SAFECOIN)
        assertEquals(address.description(), addressString)
    }

    @Test
    fun testDefaultTokenAddress() {
        val mainAddress = SafecoinAddress("B1iGmDJdvmxyUiYM8UEo2Uw2D58EmUrw4KyLYMmrhf8V")
        val tokenMintAddress = "SRMuApVNdxXokk5GT7XD5cUUgXMBCoAz2LHeuAoKWRt"
        val defaultAddress = mainAddress.defaultTokenAddress(tokenMintAddress)
        assertEquals(defaultAddress, "EDNd1ycsydWYwVmrYZvqYazFqwk1QjBgAUKFjBoz1jKP")
    }
}
