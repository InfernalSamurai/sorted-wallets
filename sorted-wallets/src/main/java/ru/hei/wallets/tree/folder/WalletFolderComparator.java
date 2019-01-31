package ru.hei.wallets.tree.folder;

import ru.hei.wallets.wallet.interfaces.WalletItem;

import java.util.Comparator;

public class WalletFolderComparator
    implements Comparator<WalletItem>
{
    @Override
    public int compare( WalletItem walletItem, WalletItem t1 )
    {
        return walletItem.getName()
                         .compareTo( t1.getName() );
    }
}
