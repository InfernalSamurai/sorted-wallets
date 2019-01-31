package ru.hei.wallets.wallet.entity;

import org.jetbrains.annotations.NotNull;
import ru.hei.wallets.wallet.interfaces.WalletItem;
import ru.hei.wallets.wallet.interfaces.WalletNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WalletFolder
    implements WalletNode, WalletItem
{
    private final String name;
    private final Date created;
    private Date updated;
    private List<WalletItem> children = new ArrayList<>();

    public WalletFolder( @NotNull String name, @NotNull Date created )
    {
        this.name = name;
        this.created = created;
        this.updated = created;
    }

    public void setUpdated( @NotNull Date updated )
    {
        this.updated = updated;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public Date getCreatedDate()
    {
        return created;
    }

    public Date getUpdatedDate()
    {
        return updated;
    }

    public List<WalletItem> getChildren()
    {
        return new ArrayList<>( children );
    }

    public void addFolder( @NotNull WalletItem node )
    {
        children.add( node );
    }
}
