package com.goodsFig.model;

import java.util.List;

public interface GoodsFigDAO_interface {
    public void insert(GoodsFigVO goodsFigVO);
    public void update(GoodsFigVO goodsFigVO);
    public void delete(Integer figNo);
    public GoodsFigVO findByPrimaryKey(Integer serialNo);
    public List<GoodsFigVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
