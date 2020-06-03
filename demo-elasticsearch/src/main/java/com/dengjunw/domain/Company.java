package com.dengjunw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 员工数量
     */
    private Integer comployees;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 区域id
     */
    private String areaId;

    /**
     * 街道id
     */
    private String streetId;

    /**
     * 社区id
     */
    private String communityId;

    /**
     * 园区id
     */
    private String gardenId;

    /**
     * 入驻时间
     */
    private Long enterTime;

    /**
     * 行业
     */
    private String industry;

    /**
     * 公司图片
     */
    private String img;

    //此字段信息在产值表中，不在企业信息表中
//    /**
//     * 营业收入
//     */
//    private BigDecimal income;

    /**
     * 营业面积
     */
    private BigDecimal busarea;

    /**
     * 是否为四上企业
     */
//    private Integer ifFourTop;

    /**
     * 企业性质 1:法人企业 2:产业活动单位
     */
    private Integer enterpriseNature;

    /**
     * 是否为本地注册企业
     */
    private Integer isLand;

    /**
     * 百度地图x坐标
     */
    private String fpoix;

    /**
     * 百度地图y坐标
     */
    private String fpoiy;

    /**
     * 企业地址
     */
    private String addr;

    /**
     * 是否高薪技术企业
     */
    private Integer isHighTechnology;

    /**
     * 是否为总部 1:总部 2:分部
     */
    private Integer isBranch;

    /**
     * 机构代码
     */
    private String companyCode;

    /**
     * 成立时间
     */
    private Long estdate;

    /**
     * 主营业务
     */
    private String cbuitem;

    /**
     * 片区
     */
    private String districtId;

    /**
     * 片区名称
     */
    private String districtName;

    /**
     * 坪山行业标签
     */
    private String industNamePs;

    /**
     * 版本区分时间维度
     */
    private String fversionid;

    //此字段信息在产值表中，不在企业信息表中
//    /**
//     * 产值
//     */
//    private BigDecimal production;
//
//    /**
//     * 产值同比
//     */
//    private BigDecimal outputTb;

    /**
     * 产业基地，目前不确定逻辑，直接添加标识字段
     *  TODO  最终确定产业基地和企业逻辑后修改
     */
    private String industrialBase;

    /**
     * 是否是百强企业
     * 去年所有企业年产值TOP100企业
     */
    private Integer topHundred;

    /**
     * 网站可以查询深圳所有高新技术企业名单：http://stic.sz.gov.cn/zxbs/zdyw/gqrd/gxjsqymd/
     */
    private Integer highTech;

    /**
     * 是否是规上企业   去年企业年产值≥2000万
     * 客户说只要企业曾经达规上标准，后续就默认延续其规上企业的标签
     */
    private Integer highLevel;

    /**
     * 网址
     */
    private String webSite;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 公司描述
     */
    private String description;

    /**
     * 经营状态
     */
    private Integer state;

    /**
     * 统一社会信用代码
     */
    private String xycode;

    /**
     * 进出口企业代码
     */
    private String inputOutputCode;

    /**
     * 营业期限
     */
    private String limitTime;

    /**
     * 参保人数
     */
    private Integer insureCount;

    /**
     * 曾用名
     */
    private String usedName;

    /**
     * 英文名
     */
    private String englishName;
    /**
     * 企业用地面积
     */
    private BigDecimal companyAreaLand;
    /**
     * 企业建筑面积
     */
    private BigDecimal companyConstruction;

    /**
     * 百强规定展示
     */
    public static final List<String> DEFAULT_INDUSTRY = Arrays.asList("新能源汽车",
            "新一代信息技术",
            "高端装备制造",
            "生物医药",
            "新材料",
            "传统制造业",
            "数字创意");
    /**
     * 产业布局分析规定展示
     */
    public static final List<String> INDUSTRY2 = Arrays.asList("新能源汽车",
            "新一代信息技术",
            "生物医药");

}
