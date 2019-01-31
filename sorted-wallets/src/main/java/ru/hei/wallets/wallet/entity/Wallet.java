package ru.hei.wallets.wallet.entity;

import ru.hei.wallets.interfaces.WalletItem;
import ru.hei.wallets.interfaces.WalletNode;

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

    public void addFolder( WalletFolder folder )
    {
        folders.add( folder );
    }
}
