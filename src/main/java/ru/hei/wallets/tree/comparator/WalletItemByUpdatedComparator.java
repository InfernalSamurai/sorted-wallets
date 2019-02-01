package ru.hei.wallets.tree.comparator;

import ru.hei.wallets.wallet.interfaces.WalletItem;

import java.util.Comparator;

public class WalletItemByUpdatedComparator
    implements Comparator<WalletItem>
{
    @Override
    public int compare( WalletItem o, WalletItem t1 )
    {
        return o.getUpdatedDate()
                .compareTo( t1.getUpdatedDate() );
    }
}
