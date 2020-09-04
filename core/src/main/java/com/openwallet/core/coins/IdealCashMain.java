package com.coinomi.core.coins;

import com.coinomi.core.coins.families.PeerFamily;

public class IdealcashMain extends PeerFamily {
    private static IdealcashMain instance = new IdealcashMain();

    private IdealcashMain() {
        id = "idealcash.main";

        addressHeader = 30;
        p2shHeader = 85;
        acceptableAddressCodes = new int[]{this.addressHeader, this.p2shHeader};
        spendableCoinbaseDepth = 520;
        dumpedPrivateKeyHeader = 128;

        name = "Idealcash"; // "TcpCoin";
        symbol = "DEAL";
        uriScheme = "idealcash";
        bip44Index = 560;
        unitExponent = 8;
        feeValue = value(10000);
        minNonDust = value(1);
        softDustLimit = value(10000);
        softDustPolicy = SoftDustPolicy.BASE_FEE_FOR_EACH_SOFT_DUST_TXO;
        signedMessageHeader = CoinType.toBytes("idealcash signed Message:\n");
    }

    public static synchronized IdealcashMain get() {
        IdealcashMain idealcashMain;
        synchronized (IdealcashMain.class) {
            idealcashMain = instance;
        }
        return idealcashMain;
    }
}
