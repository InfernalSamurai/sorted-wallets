package ru.hei.wallets.tree;

import org.jetbrains.annotations.NotNull;
import ru.hei.wallets.tree.comparator.WalletItemByNameComparator;
import ru.hei.wallets.tree.wallet.WalletForTree;
import ru.hei.wallets.wallet.entity.Wallet;

public class WalletTree
{
    private final Wallet wallet;

    public WalletTree( @NotNull Wallet wallet )
    {
        this.wallet = wallet;
    }

    private WalletForTree getWalletForTree()
    {
        return new WalletForTree( wallet );
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        getWalletForTree().getFoldersWithSortedCards()
                          .stream()
                          .sorted( new WalletItemByNameComparator() )
                          .forEach( result::append );
        return result.toString()
                     .trim();
    }
}
