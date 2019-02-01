package ru.hei.wallets;

import ru.hei.wallets.tree.WalletTree;
import ru.hei.wallets.wallet.entity.Wallet;
import ru.hei.wallets.wallet.entity.WalletCard;
import ru.hei.wallets.wallet.entity.WalletFolder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Программа будет выводить в консоль дерево с учётом сортировки.
 * Сортировка:
 * - для папок - по алфавиту,
 * - для карточек - по полю updated.
 * Отображение:
 * - для папок "<НАЗВАНИЕ>_<ДАТА_СОЗДАНИЯ>",
 * - для карточек "<НАЗВАНИЕ> (список всех пар КЛЮЧ->ЗНАЧЕНИЕ через запятую)"
 */
public class ConsoleWalletTree
{
    public static void main( String[] args )
    {
        Date nowDate = Date.from( Instant.now() );

        Map<String, String> metaInfo = new HashMap<>();
        metaInfo.put( "number", "0123 4567 8901 2345" );
        metaInfo.put( "valid thru", "12/9999" );

        WalletCard firstCard = new WalletCard( "firstCardName", nowDate );
        firstCard.setMetaInfo( metaInfo );

        Date earlierDate = Date.from( Instant.now()
                                             .minus( 1, ChronoUnit.DAYS ) );
        WalletCard secondCard = new WalletCard( "secondCardName", nowDate );
        secondCard.setMetaInfo( metaInfo );
        secondCard.setUpdated( earlierDate );

        String firstFolderName = "firstFolderName";
        String secondFolderName = "secondFolderName";
        WalletFolder firstFolder = new WalletFolder( firstFolderName, nowDate );
        WalletFolder secondFolder = new WalletFolder( secondFolderName, nowDate );

        firstFolder.addFolder( secondCard );
        firstFolder.addFolder( firstCard );

        secondFolder.addFolder( firstCard );
        secondFolder.addFolder( secondCard );

        Wallet wallet = new Wallet();
        wallet.addFolder( secondFolder );
        wallet.addFolder( firstFolder );

        System.out.println( new WalletTree( wallet ) );
    }
}
