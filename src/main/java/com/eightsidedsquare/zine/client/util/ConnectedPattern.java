package com.eightsidedsquare.zine.client.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public enum ConnectedPattern {
    AAAA("all", "aaaa"),
    AHHA("ahha"),
    HHHH("horizontal", "hhhh"),
    HAAH("haah"),
    AHCV("ahcv"),
    HAVC("havc"),
    VCCV("vccv"),
    HHCC("hhcc"),
    CNCC("cncc"),
    CCNC("ccnc"),
    NCCN("nccn"),
    NNCC("nncc"),
    AAVV("aavv"),
    AHNV("ahnv"),
    HHNN("hhnn"),
    HAVN("havn"),
    VCHA("vcha"),
    CVAH("cvah"),
    CCHH("cchh"),
    CVVC("cvvc"),
    NCCC("nccc"),
    CCCN("cccn"),
    CCNN("ccnn"),
    CNNC("cnnc"),
    VVVV("vertical", "vvvv"),
    VNNV("vnnv"),
    NNNN("none", "nnnn"),
    NVVN("nvvn"),
    VCNV("vcnv"),
    HHCN("hhcn"),
    VNCV("vncv"),
    HHNC("hhnc"),
    NNCN("nncn"),
    NNNC("nnnc"),
    CNCN("cncn"),
    NCNC("ncnc"),
    VVAA("vvaa"),
    VNHA("vnha"),
    NNHH("nnhh"),
    NVAH("nvah"),
    CNHH("cnhh"),
    NVVC("nvvc"),
    NCHH("nchh"),
    CVVN("cvvn"),
    NCNN("ncnn"),
    CNNN("cnnn"),
    CCCC("corners", "cccc");

    private static final Int2ObjectMap<ConnectedPattern> LOOKUP = createLookup();

    private final String suffix;
    private final ConnectedShape nw;
    private final ConnectedShape ne;
    private final ConnectedShape se;
    private final ConnectedShape sw;

    ConnectedPattern(String suffix, ConnectedShape nw, ConnectedShape ne, ConnectedShape se, ConnectedShape sw) {
        this.suffix = "_" + suffix;
        this.nw = nw;
        this.ne = ne;
        this.se = se;
        this.sw = sw;
    }

    ConnectedPattern(String suffix, String name) {
        this(
                suffix,
                ConnectedShape.fromChar(name.charAt(0)),
                ConnectedShape.fromChar(name.charAt(1)),
                ConnectedShape.fromChar(name.charAt(2)),
                ConnectedShape.fromChar(name.charAt(3))
        );
    }

    ConnectedPattern(String name) {
        this(name, name);
    }

    private static int getLookupId(ConnectedShape nw, ConnectedShape ne, ConnectedShape se, ConnectedShape sw) {
        return nw.ordinal() + ne.ordinal() * 5 + se.ordinal() * 25 + sw.ordinal() * 125;
    }

    private static Int2ObjectMap<ConnectedPattern> createLookup() {
        Int2ObjectMap<ConnectedPattern> lookup = new Int2ObjectArrayMap<>();
        for (ConnectedPattern pattern : ConnectedPattern.values()) {
            lookup.put(getLookupId(pattern.nw, pattern.ne, pattern.se, pattern.sw), pattern);
        }
        return lookup;
    }

    public static ConnectedPattern from(ConnectedShape nw, ConnectedShape ne, ConnectedShape se, ConnectedShape sw) {
        return LOOKUP.getOrDefault(getLookupId(nw, ne, se, sw), AAAA);
    }

    public boolean contains(ConnectedShape shape) {
        return this.nw == shape || this.ne == shape || this.se == shape || this.sw == shape;
    }

    public boolean allMatch() {
        return this.nw == this.ne && this.ne == this.se && this.se == this.sw;
    }

    public Identifier addSuffix(Identifier id) {
        return id.withSuffixedPath(this.suffix);
    }

    public ConnectedShape getNW() {
        return this.nw;
    }

    public ConnectedShape getNE() {
        return this.ne;
    }

    public ConnectedShape getSE() {
        return this.se;
    }

    public ConnectedShape getSW() {
        return this.sw;
    }

    public ConnectedPattern flipHorizontal() {
        return switch (this) {
            case AHHA -> HAAH;
            case HAAH -> AHHA;
            case AHCV -> HAVC;
            case HAVC -> AHCV;
            case VCCV -> CVVC;
            case CNCC -> NCCC;
            case CCNC -> CCCN;
            case NCCN -> CNNC;
            case AHNV -> HAVN;
            case HAVN -> AHNV;
            case VCHA -> CVAH;
            case CVAH -> VCHA;
            case CVVC -> VCCV;
            case NCCC -> CNCC;
            case CCCN -> CCNC;
            case CNNC -> NCCN;
            case VNNV -> NVVN;
            case NVVN -> VNNV;
            case VCNV -> CVVN;
            case HHCN -> HHNC;
            case VNCV -> NVVC;
            case HHNC -> HHCN;
            case NNCN -> NNNC;
            case NNNC -> NNCN;
            case CNCN -> NCNC;
            case NCNC -> CNCN;
            case VNHA -> NVAH;
            case NVAH -> VNHA;
            case CNHH -> NCHH;
            case NVVC -> VNCV;
            case NCHH -> CNHH;
            case CVVN -> VCNV;
            case NCNN -> CNNN;
            case CNNN -> NCNN;
            default -> this;
        };
    }

    public ConnectedPattern flipVertical() {
        return switch (this) {
            case AHCV -> VCHA;
            case HAVC -> CVAH;
            case HHCC -> CCHH;
            case CNCC -> CCNC;
            case CCNC -> CNCC;
            case NNCC -> CCNN;
            case AAVV -> VVAA;
            case AHNV -> VNHA;
            case HHNN -> NNHH;
            case HAVN -> NVAH;
            case VCHA -> AHCV;
            case CVAH -> HAVC;
            case CCHH -> HHCC;
            case NCCC -> CCCN;
            case CCCN -> NCCC;
            case CCNN -> NNCC;
            case VCNV -> VNCV;
            case HHCN -> NCHH;
            case VNCV -> VCNV;
            case HHNC -> CNHH;
            case NNCN -> NCNN;
            case NNNC -> CNNN;
            case CNCN -> NCNC;
            case NCNC -> CNCN;
            case VVAA -> AAVV;
            case VNHA -> AHNV;
            case NNHH -> HHNN;
            case NVAH -> HAVN;
            case CNHH -> HHNC;
            case NVVC -> CVVN;
            case NCHH -> HHCN;
            case CVVN -> NVVC;
            case NCNN -> NNCN;
            case CNNN -> NNNC;
            default -> this;
        };
    }
}
