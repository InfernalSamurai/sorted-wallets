package ru.hei.wallets.tree.wallet;

import ru.hei.wallets.tree.folder.WalletFolderForTree;
import ru.hei.wallets.wallet.entity.Wallet;
import ru.hei.wallets.wallet.entity.WalletFolder;

import java.util.List;
import java.util.stream.Collectors;

public class WalletForTree
    extends Wallet
{
    public WalletForTree( Wallet wallet )
    {
        wallet.getChildren()
              .forEach( walletItem -> addFolder( (WalletFolder)walletItem ) );
    }

    public List<WalletFolderForTree> getFoldersWithSortedCards()
    {
        return getChildren().stream()
                            .map( walletItem -> new WalletFolderForTree( (WalletFolder)walletItem ) )
                            .collect( Collectors.toList() );
    }
}
