package com.supplier.model;

import java.util.List;

public interface SupplierDAO_interface {

	public void insert(SupplierVO supplierVO);
	public void update(SupplierVO supplierVO);
	public void delete(Integer supplierVO);
	public SupplierVO findByPrimaryKey(Integer supplierVO);
	public List<SupplierVO> getAll();
}
