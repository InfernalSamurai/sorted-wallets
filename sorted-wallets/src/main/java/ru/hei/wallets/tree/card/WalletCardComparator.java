package ru.hei.wallets.tree.card;

import ru.hei.wallets.wallet.entity.WalletCard;

import java.util.Comparator;

public class WalletCardComparator
    implements Comparator<WalletCard>
{
    @Override
    public int compare( WalletCard o, WalletCard t1 )
    {
        return o.getUpdatedDate()
                .compareTo( t1.getUpdatedDate() );
    }
}
