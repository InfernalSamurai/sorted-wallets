package ru.hei.wallets.entity;

import ru.hei.wallets.interfaces.WalletItem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WalletCard
    implements WalletItem
{
    private final String name;
    private final Date created;
    private Date updated;
    private Map<String, String> metaInfo = new HashMap<>();

    public WalletCard( String name, Date created )
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

    public Map<String, String> getMetaInfo()
    {
        return metaInfo;
    }

    public void setMetaInfo( Map<String, String> metaInfo )
    {
        this.metaInfo = metaInfo;
    }
}
