package ru.hei.wallets.tree.card;

import ru.hei.wallets.wallet.entity.WalletCard;

public class WalletCardForTree
    extends WalletCard
{
    public WalletCardForTree( WalletCard card )
    {
        super( card.getName(), card.getCreatedDate() );
        super.setUpdated( card.getUpdatedDate() );
        super.setMetaInfo( card.getMetaInfo() );
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder().append( getName() );
        result.append( getStringOfMetaIfExist() );
        result.append( System.lineSeparator() );
        return result.toString();
    }

    private String getStringOfMetaIfExist()
    {
        if( getMetaInfo().isEmpty() )
        {
            return "";
        }

        StringBuilder result = new StringBuilder();

        result.append( " (" );
        getMetaInfo().forEach( ( key, value ) -> result.append( key )
                                                       .append( "->" )
                                                       .append( value )
                                                       .append( "," ) );
        result.deleteCharAt( result.lastIndexOf( "," ) );
        result.append( ")" );

        return result.toString();
    }
}
