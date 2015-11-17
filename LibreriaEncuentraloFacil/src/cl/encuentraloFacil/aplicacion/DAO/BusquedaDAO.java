/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.BusquedaTO;
import cl.encuentraloFacil.aplicacion.TO.EmpresaGeoTO;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.SubFamProductosTO;
import cl.encuentraloFacil.aplicacion.TO.SubFamiliaProdTO;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class BusquedaDAO implements Serializable {
    final static Logger logger = Logger.getLogger(BusquedaDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;

    public List<BusquedaTO> getBusquedaProd(int emp, int prod) {
        logger.info("getBusquedaProd");
        List<BusquedaTO> listBusqueda = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement("select * from producto as a, empresa as b \n" +
            "where a.idproduc = "+prod+" and\n" +
            "b.idemp = "+emp+"");
            rs = cst.executeQuery();
            while (rs.next()) {
                BusquedaTO busqueda = new BusquedaTO();
                busqueda.getProducto().setIdProducto(rs.getInt("idproduc"));
                busqueda.getProducto().setNombreProducto(rs.getString("nomprod"));
                busqueda.getProducto().setPrecio(rs.getDouble("precio"));
                busqueda.getEmpresa().setIdEmpresa(rs.getInt("idemp"));
                busqueda.getEmpresa().setRutEmp(rs.getInt("rutemp"));
                busqueda.getEmpresa().setDv(rs.getString("dvemp"));
                busqueda.getEmpresa().setNombreEmpresa(rs.getString("nomb"));
                busqueda.getEmpresa().setUrl(rs.getString("urlemp"));
                
                listBusqueda.add(busqueda);

            }
        } catch (SQLException e) {
            logger.error("[ERROR] getBusquedaProd SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getBusquedaProd: " + e.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                } if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] getBusquedaProd: " + ex.getMessage());
            } 
        }
        return listBusqueda;
    }
    
    public List<FamiliaProdTO> getBusquedaFamiliaEmpre(int valorx) {
        logger.info("getBusquedaFamiliaEmpre");
        List<FamiliaProdTO> listFamiliaProd = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement("select distinct d.idfamilia ,d.nomfam from  producto_empresa as a,subfamilia as b, producto as c , familia as d\n" +
            "where b.idSubFamilia = c.SubFamilia_idSubFamilia and\n" +
            "a.producto_idproduc = c.idproduc and\n" +
            "d.idfamilia = b.Familia_idFamilia\n" +
            "and a.Empresa_idemp = "+valorx+";");
            rs = cst.executeQuery();
            while (rs.next()) {
                FamiliaProdTO familia = new FamiliaProdTO();
                familia.setIdFam(rs.getInt(1));
                familia.setNomFam(rs.getString(2));
                listFamiliaProd.add(familia);
            }    
        } catch (SQLException e) {
            logger.error("[ERROR] getBusquedaFamiliaEmpre SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getBusquedaFamiliaEmpre: " + e.getMessage());
        } finally {
            try {
                 if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                } if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] getBusquedaFamiliaEmpre: " + ex.getMessage());
            } 
        }
        return listFamiliaProd;
    }
    
    public List<SubFamProductosTO> getBusquedaProductosSubfamilia(int idempresa, int idsubfamilia) {
        logger.info("getBusquedaProductosSubfamilia");
        List<SubFamProductosTO> listProductos = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement("select distinct c.idproduc, c.nomprod, c.precio from  producto_empresa as a,subfamilia as b, producto as c \n" +
            "where a.producto_idproduc = c.idproduc " +
            "and c.SubFamilia_idSubFamilia = "+idsubfamilia+"\n" +
            "and a.Empresa_idemp = "+idempresa+";");
            rs = cst.executeQuery();
            while (rs.next()) {
                SubFamProductosTO productos = new SubFamProductosTO();
                productos.setIdProducto(rs.getInt(1));
                productos.setNombreProducto(rs.getString(2));
                productos.setPrecio(rs.getDouble(3));
                listProductos.add(productos);
            }    
        } catch (SQLException e) {
            logger.error("[ERROR] getBusquedaProductosSubfamilia SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getBusquedaProductosSubfamilia: " + e.getMessage());
        } finally {
            try {
                 if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                } if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] getBusquedaProductosSubfamilia: " + ex.getMessage());
            } 
        }
        return listProductos;
    }
    
    public List<SubFamiliaProdTO> getBusquedaSubFamiliaEmpre(int emp, int fam) {
        logger.info("getBusquedaSubFamiliaEmpre");
        List<SubFamiliaProdTO> listSubFamiliaProd = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement("select distinct b.idSubFamilia, b.nomsubfam from  producto_empresa as a,subfamilia as b, producto as c , familia as d\n" +
            "where b.idSubFamilia = c.SubFamilia_idSubFamilia and\n" +
            "a.producto_idproduc = c.idproduc and\n" +
            "b.Familia_idFamilia = "+fam+"\n" +
            "and a.Empresa_idemp = "+emp+";");
            rs = cst.executeQuery();
            while (rs.next()) {
                SubFamiliaProdTO subFamilia = new SubFamiliaProdTO();
                subFamilia.setIdSubFam(rs.getInt(1));
                subFamilia.setNomSubFam(rs.getString(2));
                listSubFamiliaProd.add(subFamilia);

            }
        } catch (SQLException e) {
            logger.error("[ERROR] getBusquedaSubFamiliaEmpre SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getBusquedaSubFamiliaEmpre: " + e.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                } if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] getBusquedaSubFamiliaEmpre: " + ex.getMessage());
            } 
        }
        return listSubFamiliaProd;
    }
    
    //connect to DB and get customer list
    public List<EmpresaGeoTO> getEmpresas(String nomprod, double lat, double lng)  {
        logger.info("getEmpresas");
        List<EmpresaGeoTO> resultadoFinal = new ArrayList();
        double difdist = 0;
        try {
            
            cst = conn.getConnection().prepareStatement("select b.idemp,b.nomb,c.latitud,c.longitud,b.urlemp,a.idproduc \n"
                    + "from producto as a,empresa as b, emprgeo as c, producto_empresa as d\n"
                    + "where d.producto_idproduc = a.idproduc\n"
                    + "and d.empresa_idemp = b.idemp \n"
                    + "and b.idemp = c.Empresa_idemp\n"
                    + "and a.nomprod = '"+nomprod+"';");

            rs = cst.executeQuery();

            while (rs.next()) {
                EmpresaGeoTO empresas = new EmpresaGeoTO();
                difdist = distFinal(lat, lng, rs.getDouble(3), rs.getDouble(4));
                if (difdist <= 100.0) {
                    empresas.setIdem(rs.getInt(1));
                    empresas.setNombre(rs.getString(2));
                    empresas.setLat(rs.getDouble(3));
                    empresas.setLng(rs.getDouble(4));
                    empresas.setLink(rs.getString(5));
                    empresas.setIdprod(Integer.valueOf(rs.getString(6)));
                    resultadoFinal.add(empresas);
                } else {
                    System.out.print("la empresa" + rs.getString(2) + " no fue agregada porque su distancia es " + difdist + "");
                }
            }
        } catch (SQLException e) {
            logger.error("[ERROR] getEmpresas SQLException: " + e.getMessage());
        } finally {
            try {
                 if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] getEmpresas: " + ex.getMessage());
            } 
        }

        return resultadoFinal;
    }

    public double distFinal(double lat1, double lng1, double lat2, double lng2) {
        logger.info("distFinal");
        //double earthRadius = 3958.75;//miles  
        double earthRadius = 6371;//kilometers  
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;
        
        logger.info("distFinal es un valor de : " + dist);
        return dist;
    }

    /**
     *
     * @param nombreProd
     * @return
     */
    public List<ProductoTO> autoComProd(String nombreProd) {
        logger.info("autoComProd");
        List<ProductoTO> resultadoFinal = new ArrayList();
        try {
            
             cst = conn.getConnection().prepareStatement("select idproduc, nomprod from producto where nomprod like '%" + nombreProd + "%';");
            //ps.setString(1, nombreProd);
            rs = cst.executeQuery();
            while (rs.next()) {
                ProductoTO prod = new ProductoTO();
                prod.setIdProducto(rs.getInt(1));
                prod.setNombreProducto(rs.getString(2));
                resultadoFinal.add(prod);
            }
        } catch (SQLException e) {
            logger.error("[ERROR] autoComProd SQLException: " + e.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                } if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] autoComProd: " + ex.getMessage());
            } 
        }
        return resultadoFinal;
    }

}
