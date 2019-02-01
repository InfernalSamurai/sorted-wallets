package ru.hei.wallets.wallet.entity;

import org.jetbrains.annotations.NotNull;
import ru.hei.wallets.wallet.interfaces.WalletItem;
import ru.hei.wallets.wallet.interfaces.WalletNode;

import java.util.ArrayList;
import java.util.List;

public class Wallet
    implements WalletNode
{
    private List<WalletFolder> folders = new ArrayList<>();

    public List<WalletItem> getChildren()
    {
        return new ArrayList<>( folders );
    }

    public void addFolder( @NotNull WalletFolder folder )
    {
        folders.add( folder );
    }
}
