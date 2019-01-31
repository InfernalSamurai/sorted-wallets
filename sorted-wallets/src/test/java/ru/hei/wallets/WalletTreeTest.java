package ru.hei.wallets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hei.wallets.tree.WalletTree;
import ru.hei.wallets.wallet.entity.Wallet;
import ru.hei.wallets.wallet.entity.WalletCard;
import ru.hei.wallets.wallet.entity.WalletFolder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class WalletTreeTest
{
    private static WalletCard getTestCard( String cardName, Date date )
    {
        return new WalletCard( cardName, date );
    }

    private static WalletFolder getTestWalletFolder( String folderName, Date date )
    {
        return new WalletFolder( folderName, date );
    }

    @Test
    void walletTreePrint()
    {
        Wallet wallet = new Wallet();
        Instant instant = LocalDateTime.of( 2019, 1, 30, 0, 0, 0 )
                                       .atZone( ZoneId.systemDefault() )
                                       .toInstant();

        WalletCard walletCardFirst = getTestCard( "testCardName1", Date.from( instant ) );
        Map<String, String> metaInfoFirst = new HashMap<>();
        metaInfoFirst.put( "number", "9956" );
        metaInfoFirst.put( "before", "2012-03-03" );
        walletCardFirst.setMetaInfo( metaInfoFirst );

        WalletCard walletCardSecond = getTestCard( "testCardName2", Date.from( instant ) );
        Map<String, String> metaInfoSecond = new HashMap<>();
        metaInfoSecond.put( "number", "9957" );
        metaInfoSecond.put( "before", "2013-04-04" );
        walletCardSecond.setMetaInfo( metaInfoSecond );
        walletCardSecond.setUpdated( Date.from( instant.minus( 1, ChronoUnit.DAYS ) ) );

        String folderName = "AtestFolderName";
        WalletFolder walletFolderFirst = getTestWalletFolder( folderName, Date.from( instant ) );
        walletFolderFirst.addFolder( walletCardFirst );
        walletFolderFirst.addFolder( walletCardSecond );

        WalletFolder walletFolderSecond = getTestWalletFolder( "BtestFolderName", Date.from( instant ) );
        walletFolderSecond.addFolder( walletCardSecond );
        walletFolderSecond.addFolder( walletCardFirst );

        wallet.addFolder( walletFolderSecond );
        wallet.addFolder( walletFolderFirst );

        StringBuilder expected = new StringBuilder().append( "AtestFolderName_2019-01-30" )
                                                    .append( System.lineSeparator() )
                                                    .append( "    testCardName2" )
                                                    .append( " (number->9957,before->2013-04-04)" )
                                                    .append( System.lineSeparator() )
                                                    .append( "    testCardName1" )
                                                    .append( " (number->9956,before->2012-03-03)" )
                                                    .append( System.lineSeparator() )
                                                    .append( "BtestFolderName_2019-01-30" )
                                                    .append( System.lineSeparator() )
                                                    .append( "    testCardName2" )
                                                    .append( " (number->9957,before->2013-04-04)" )
                                                    .append( System.lineSeparator() )
                                                    .append( "    testCardName1" )
                                                    .append( " (number->9956,before->2012-03-03)" );

        WalletTree walletTree = new WalletTree( wallet );
        Assertions.assertEquals( expected.toString(), walletTree.toString() );
    }

    @Test
    void cardWithEmptyMap()
    {
        String indent = "    ";
        String cardName = "testCard";
        String folderName = "testFolder";
        Date date = new Date();

        WalletCard card = getTestCard( cardName, date );
        card.setMetaInfo( Collections.emptyMap() );

        WalletFolder walletFolder = getTestWalletFolder( folderName, date );
        walletFolder.addFolder( card );

        Wallet wallet = new Wallet();
        wallet.addFolder( walletFolder );

        StringBuilder expected = new StringBuilder();
        expected.append( folderName )
                .append( "_" )
                .append( date.toInstant()
                             .atZone( ZoneId.systemDefault() )
                             .toLocalDate() )
                .append( System.lineSeparator() )
                .append( indent )
                .append( cardName );

        Assertions.assertEquals( expected.toString(), new WalletTree( wallet ).toString() );
    }

    @Test
    void folderWithFolderAsCardReturnOnlyFolder()
    {
        String folderName = "testFolder";
        Date date = new Date();

        WalletFolder walletFolderParent = getTestWalletFolder( folderName, date );
        WalletFolder walletFolderChild = getTestWalletFolder( folderName, date );
        walletFolderParent.addFolder( walletFolderChild );

        Wallet wallet = new Wallet();
        wallet.addFolder( walletFolderParent );

        StringBuilder expected = new StringBuilder();
        expected.append( folderName )
                .append( "_" )
                .append( date.toInstant()
                             .atZone( ZoneId.systemDefault() )
                             .toLocalDate() );
        Assertions.assertEquals( expected.toString(), new WalletTree( wallet ).toString() );
    }
}

