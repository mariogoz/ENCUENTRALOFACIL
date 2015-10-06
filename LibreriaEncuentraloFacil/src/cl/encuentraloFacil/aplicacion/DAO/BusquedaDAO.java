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

/**
 *
 * @author Mario
 */
public class BusquedaDAO implements Serializable {

    Conexion conn = new Conexion();
    PreparedStatement cst = null;

    public List<BusquedaTO> getBusquedaProd(int emp, int prod) {
        List<BusquedaTO> listBusqueda = new ArrayList<>();
        try {
            cst = conn.getConnection().prepareStatement("select * from producto as a, empresa as b \n" +
            "where a.idproduc = "+prod+" and\n" +
            "b.idemp = "+emp+"");
            ResultSet rs = cst.executeQuery();
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
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } 
        }
        return listBusqueda;
    }
    
    public List<FamiliaProdTO> getBusquedaFamiliaEmpre(int valorx) {
        List<FamiliaProdTO> listFamiliaProd = new ArrayList<>();
        try {
            cst = conn.getConnection().prepareStatement("select distinct d.idfamilia ,d.nomfam from  producto_empresa as a,subfamilia as b, producto as c , familia as d\n" +
            "where b.idSubFamilia = c.SubFamilia_idSubFamilia and\n" +
            "a.producto_idproduc = c.idproduc and\n" +
            "d.idfamilia = b.Familia_idFamilia\n" +
            "and a.Empresa_idemp = "+valorx+";");
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                FamiliaProdTO familia = new FamiliaProdTO();
                familia.setIdFam(rs.getInt(1));
                familia.setNomFam(rs.getString(2));
                listFamiliaProd.add(familia);
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                 if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } 
        }
        return listFamiliaProd;
    }
    
    public List<SubFamProductosTO> getBusquedaProductosSubfamilia(int idempresa, int idsubfamilia) {
        List<SubFamProductosTO> listProductos = new ArrayList<>();
        try {
            cst = conn.getConnection().prepareStatement("select distinct c.idproduc, c.nomprod, c.precio from  producto_empresa as a,subfamilia as b, producto as c \n" +
            "where a.producto_idproduc = c.idproduc " +
            "and c.SubFamilia_idSubFamilia = "+idsubfamilia+"\n" +
            "and a.Empresa_idemp = "+idempresa+";");
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                SubFamProductosTO productos = new SubFamProductosTO();
                productos.setIdProducto(rs.getInt(1));
                productos.setNombreProducto(rs.getString(2));
                productos.setPrecio(rs.getDouble(3));
                listProductos.add(productos);
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                 if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } 
        }
        return listProductos;
    }
    
    public List<SubFamiliaProdTO> getBusquedaSubFamiliaEmpre(int emp, int fam) {
        List<SubFamiliaProdTO> listSubFamiliaProd = new ArrayList<>();
        try {
            cst = conn.getConnection().prepareStatement("select distinct b.idSubFamilia, b.nomsubfam from  producto_empresa as a,subfamilia as b, producto as c , familia as d\n" +
            "where b.idSubFamilia = c.SubFamilia_idSubFamilia and\n" +
            "a.producto_idproduc = c.idproduc and\n" +
            "b.Familia_idFamilia = "+fam+"\n" +
            "and a.Empresa_idemp = "+emp+";");
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                SubFamiliaProdTO subFamilia = new SubFamiliaProdTO();
                subFamilia.setIdSubFam(rs.getInt(1));
                subFamilia.setNomSubFam(rs.getString(2));
                listSubFamiliaProd.add(subFamilia);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } 
        }
        return listSubFamiliaProd;
    }
    
    //connect to DB and get customer list
    public List<EmpresaGeoTO> getEmpresas(String nomprod, double lat, double lng)  {
        List<EmpresaGeoTO> resultadoFinal = new ArrayList<EmpresaGeoTO>();
        double difdist = 0;
        try {
            
            cst = conn.getConnection().prepareStatement("select b.idemp,b.nomb,c.latitud,c.longitud,b.urlemp,a.idproduc \n"
                    + "from producto as a,empresa as b, emprgeo as c, producto_empresa as d\n"
                    + "where d.producto_idproduc = a.idproduc\n"
                    + "and d.empresa_idemp = b.idemp \n"
                    + "and b.idemp = c.Empresa_idemp\n"
                    + "and a.nomprod = '"+nomprod+"';");

            ResultSet rs = cst.executeQuery();

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
            e.printStackTrace();
        } finally {
            try {
                 if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } 
        }

        return resultadoFinal;
    }

    public double distFinal(double lat1, double lng1, double lat2, double lng2) {

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

        return dist;
    }

    /**
     *
     * @param nombreProd
     * @return
     */
    public List<ProductoTO> autoComProd(String nombreProd) {
        List<ProductoTO> resultadoFinal = new ArrayList<ProductoTO>();
        PreparedStatement ps = null;
        try {
            
             cst = conn.getConnection().prepareStatement("select idproduc, nomprod from producto where nomprod like '%" + nombreProd + "%';");
            //ps.setString(1, nombreProd);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                ProductoTO prod = new ProductoTO();
                prod.setIdProducto(rs.getInt(1));
                prod.setNombreProducto(rs.getString(2));
                resultadoFinal.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } 
        }
        return resultadoFinal;
    }

}
