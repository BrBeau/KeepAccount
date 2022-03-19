package com.byron.keepaccount.bean;

/**
 * 收入支出GridView里的类型数据
 * @author Byron
 * @date 220302
 */
public class TypeBean {
    public int id;
    public String typeName; //选中类型的名称
    public int selectImageId; //未选中类型图片的id
    public int selectedImageId; //选中类型图片的id
    public int kind; //收入：1，支出：0

    public TypeBean(int id, String typeName, int selectImageId, int selectedImageId, int kind){
        this.id = id;
        this.typeName = typeName;
        this.selectImageId = selectImageId;
        this.selectedImageId = selectedImageId;
        this.kind = kind;
    }


    @Override
    public String toString() {
        return "TypeBean{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", selectImageId=" + selectImageId +
                ", selectedImageId=" + selectedImageId +
                ", kind=" + kind +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getSelectImageId() {
        return selectImageId;
    }

    public void setSelectImageId(int selectImageId) {
        this.selectImageId = selectImageId;
    }

    public int getSelectedImageId() {
        return selectedImageId;
    }

    public void setSelectedImageId(int selectedImageId) {
        this.selectedImageId = selectedImageId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
