package com.supplier.model;

import java.util.List;

public class SupplierService {

	private SupplierDAO_interface dao;

	public SupplierService() {
		dao = new SupplierJDBCDAO();
	}

	public SupplierVO addSupplier(String supplyName, String supplyContact,
			String supplyPhone, String supplyAddress) {

		SupplierVO supplierVO = new SupplierVO();

		supplierVO.setSupplyName(supplyName);
		supplierVO.setSupplyContact(supplyContact);
		supplierVO.setSupplyPhone(supplyPhone);
		supplierVO.setSupplyAddress(supplyAddress);
		dao.insert(supplierVO);

		return supplierVO;
	}

	public SupplierVO updateSupplier(Integer supplyNo, String supplyName, String supplyContact,
			String supplyPhone, String supplyAddress) {

		SupplierVO supplierVO = new SupplierVO();

		supplierVO.setSupplyNo(supplyNo);
		supplierVO.setSupplyName(supplyName);
		supplierVO.setSupplyContact(supplyContact);
		supplierVO.setSupplyPhone(supplyPhone);
		supplierVO.setSupplyAddress(supplyAddress);
		dao.update(supplierVO);

		return supplierVO;
	}

	public void deleteSupplier(Integer supplyNo) {
		dao.delete(supplyNo);
	}

	public SupplierVO getOneSupplier(Integer supplyNo) {
		return dao.findByPrimaryKey(supplyNo);
	}

	public List<SupplierVO> getAll() {
		return dao.getAll();
	}
	
}
