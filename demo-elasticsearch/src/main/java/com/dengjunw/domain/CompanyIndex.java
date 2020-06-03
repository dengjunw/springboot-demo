package com.dengjunw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "company_search", createIndex = true)
public class CompanyIndex implements Serializable {

    @Id
    private Integer id;
    /**
     * 公司名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String companyName;

    /**
     * 联系人
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String contacts;

    /**
     * 联系人电话
     */
    @Field(type = FieldType.Keyword)
    private String phone;

    /**
     * 行业
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String industry;

    /**
     * 公司图片
     */
    @Field(type = FieldType.Keyword)
    private String img;

    /**
     * 企业性质 1:法人企业 2:产业活动单位
     */
    @Field(type = FieldType.Integer)
    private Integer enterpriseNature;

    /**
     * 是否为本地注册企业
     */
    @Field(type = FieldType.Integer)
    private Integer isLand;

    /**
     * 百度地图x坐标
     */
    @Field(type = FieldType.Keyword)
    private String fpoix;

    /**
     * 百度地图y坐标
     */
    @Field(type = FieldType.Keyword)
    private String fpoiy;

    /**
     * 企业地址
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String addr;

    /**
     * 是否高薪技术企业
     */
    @Field(type = FieldType.Integer)
    private Integer isHighTechnology;

    /**
     * 是否为总部 1:总部 2:分部
     */
    @Field(type = FieldType.Integer)
    private Integer isBranch;

    /**
     * 机构代码
     */
    @Field(type = FieldType.Keyword)
    private String companyCode;

    /**
     * 成立时间
     */
    @Field(type = FieldType.Long)
    private Long estdate;

    /**
     * 主营业务
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String cbuitem;

    /**
     * 片区名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String districtName;

    /**
     * 坪山行业标签
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String industNamePs;

    /**
     * 产业基地，目前不确定逻辑，直接添加标识字段
     *  TODO  最终确定产业基地和企业逻辑后修改
     */
    @Field(type = FieldType.Keyword)
    private String industrialBase;

    /**
     * 是否是百强企业
     * 去年所有企业年产值TOP100企业
     */
    @Field(type = FieldType.Integer)
    private Integer topHundred;

    /**
     * 网站可以查询深圳所有高新技术企业名单：http://stic.sz.gov.cn/zxbs/zdyw/gqrd/gxjsqymd/
     */
    @Field(type = FieldType.Integer)
    private Integer highTech;

    /**
     * 是否是规上企业   去年企业年产值≥2000万
     * 客户说只要企业曾经达规上标准，后续就默认延续其规上企业的标签
     */
    @Field(type = FieldType.Integer)
    private Integer highLevel;

    /**
     * 邮箱
     */
    @Field(type = FieldType.Keyword)
    private String email;

    /**
     * 公司描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;

    /**
     * 经营状态
     */
    @Field(type = FieldType.Integer)
    private Integer state;

    /**
     * 统一社会信用代码
     */
    @Field(type = FieldType.Keyword)
    private String xycode;

    /**
     * 注册号
     */
    @Field(type = FieldType.Keyword)
    private String entRegNo;

    /**
     * 商事主体类型
     */
    @Field(type = FieldType.Keyword)
    private String businessType;
    /**
     * 行业代码
     */
    @Field(type = FieldType.Keyword)
    private String hydm;

    /**
     * 批准或核准机构
     */
    @Field(type = FieldType.Keyword)
    private String issueUnit;

    /**
     * 经营范围
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String jyfw;
    /**
     * 法人名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String frName;
    /**
     * 注册资本
     */
    @Field(type = FieldType.Long)
    private Long zczb;

    /**
     * 是否有效
     */
    @Field(type = FieldType.Integer)
    private Integer valid;

    /**
     * 数据入库时间
     */
    @Field(type = FieldType.Long)
    private Long sysInsertTime;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String focusUser;

    @Field(type = FieldType.Integer)
    private Integer focus;

    public CompanyIndex(Company company) {
        this.id = company.getId();
        this.companyCode = company.getCompanyCode();
        this.companyName = company.getCompanyName();
        this.phone = company.getPhone();
        this.contacts = company.getContacts();
        this.industry = company.getIndustry();
        this.addr = company.getAddr();
        this.email = company.getEmail();
        this.estdate = company.getEstdate();
        this.xycode = company.getXycode();
        this.description = company.getDescription();
        this.cbuitem = company.getCbuitem();
        this.districtName = company.getDistrictName();
        this.enterpriseNature = company.getEnterpriseNature();
        this.fpoix = company.getFpoix();
        this.fpoiy = company.getFpoiy();
        this.highLevel = company.getHighLevel();
        this.topHundred = company.getTopHundred();
        this.highTech = company.getHighTech();
        this.sysInsertTime = company.getEnterTime();
        this.img = company.getImg();
        this.isBranch = company.getIsBranch();
        this.isHighTechnology = company.getIsHighTechnology();
        this.isLand = company.getIsLand();
        this.industNamePs = company.getIndustNamePs();
        this.state = company.getState();
        this.industrialBase = company.getIndustrialBase();
    }

}
