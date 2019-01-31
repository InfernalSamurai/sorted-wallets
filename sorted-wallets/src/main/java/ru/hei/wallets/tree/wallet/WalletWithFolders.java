package ru.hei.wallets.tree.wallet;

import ru.hei.wallets.tree.folder.WalletFolderWithCards;
import ru.hei.wallets.wallet.entity.Wallet;
import ru.hei.wallets.wallet.entity.WalletFolder;

import java.util.List;
import java.util.stream.Collectors;

public class WalletWithFolders
    extends Wallet
{
    public WalletWithFolders( Wallet wallet )
    {
        wallet.getChildren()
              .forEach( walletItem -> addFolder( (WalletFolder)walletItem ) );
    }

    public List<WalletFolderWithCards> getFoldersWithSortedCards()
    {
        return getChildren().stream()
                            .map( walletItem -> new WalletFolderWithCards( (WalletFolder)walletItem ) )
                            .collect( Collectors.toList() );
    }
}
