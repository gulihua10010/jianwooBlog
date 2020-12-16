package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.Menu;

import java.util.List;

/**
 * @author gulihua
 */
public class MenuExt extends Menu {
    private static final long serialVersionUID = 4753139842991520579L;
    private List<MenuExt> subMenus;

    public List<MenuExt> getSubMenus() {
        return subMenus;
    }


    public void setSubMenus(List<MenuExt> subMenus) {
        this.subMenus = subMenus;
    }


    @Override
    public String toString() {
        String str = "";
        if (super.getOid() != null) {
            str += (super.getOid().toString() + "--");

        }
        if (getSubMenus() != null) {
            for (MenuExt m : this.subMenus) {
                str += (m.getOid() + ",");
            }
        }
        return str;
    }
}
