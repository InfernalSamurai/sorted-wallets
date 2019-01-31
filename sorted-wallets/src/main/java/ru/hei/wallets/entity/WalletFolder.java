package ru.hei.wallets.entity;

import ru.hei.wallets.interfaces.WalletItem;
import ru.hei.wallets.interfaces.WalletNode;

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

    public WalletFolder( String name, Date created )
    {
        this.name = name;
        this.created = created;
        this.updated = created;
    }

    public void setUpdated( Date updated )
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

    public void addFolder( WalletItem node )
    {
        children.add( node );
    }
}
