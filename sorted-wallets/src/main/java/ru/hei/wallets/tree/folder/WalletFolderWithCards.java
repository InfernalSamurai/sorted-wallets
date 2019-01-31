package ru.hei.wallets.tree.folder;

import ru.hei.wallets.tree.card.WalletCardComparator;
import ru.hei.wallets.tree.card.WalletCardForTree;
import ru.hei.wallets.wallet.entity.WalletCard;
import ru.hei.wallets.wallet.entity.WalletFolder;
import ru.hei.wallets.wallet.interfaces.WalletItem;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class WalletFolderWithCards
    extends WalletFolder
{
    public WalletFolderWithCards( WalletFolder folder )
    {
        super( folder.getName(), folder.getCreatedDate() );
        super.setUpdated( folder.getUpdatedDate() );
        folder.getChildren()
              .forEach( super::addFolder );
    }

    private List<WalletCardForTree> getCards()
    {
        return getChildren().stream()
                            .filter( this::isWalletCard )
                            .map( walletItem -> new WalletCardForTree( (WalletCard)walletItem ) )
                            .collect( Collectors.toList() );
    }

    private boolean isWalletCard( WalletItem walletItem )
    {
        return walletItem instanceof WalletCard;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append( getName() )
              .append( "_" )
              .append( getCreatedDate().toInstant()
                                       .atZone( ZoneId.systemDefault() )
                                       .toLocalDate() )
              .append( System.lineSeparator() );
        getCards().stream()
                  .sorted( new WalletCardComparator() )
                  .forEach( card -> result.append( "    " )
                                          .append( card ) );

        return result.toString();
    }
}
