import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


class Trabajo {
    int codigo;
    String descripcion;
    double costo;
    String fechaIngreso;
    String fechaEntrega;
    String estado;

    public Trabajo(int codigo, String descripcion, double costo, String fechaIngreso, String fechaEntrega, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }
}
//Nodo base para hacer la lista enlazada
class NodoTrabajo {
    Trabajo dato;
    NodoTrabajo sig;        //referencia del siguiente nodo

    public NodoTrabajo(Trabajo dato) {
        this.dato = dato;
        this.sig = null;
    }
}

class ListaTrabajos {
    NodoTrabajo cabeza;

    public ListaTrabajos() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
    //aqui inserta al final de la lista
    public void insertar(Trabajo t) {
        NodoTrabajo nuevo = new NodoTrabajo(t);

        //si la lista esta vacia el nuevo es la cabeza
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoTrabajo aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    //busca un trabajo por código
    public Trabajo buscarPorCodigo(int codigo) {
        NodoTrabajo aux = cabeza;
        while (aux != null) {
            if (aux.dato.codigo == codigo) {
                return aux.dato; //encontrado
            }
            aux = aux.sig;
        }
        return null; //no enocontrado
    }

    //recorre toda la lista para mostrar datos
    public String mostrarTodos() {
        NodoTrabajo aux = cabeza;
        String res = "";

        if (aux == null) {
            return "No hay trabajos registrados.";
        }

        while (aux != null) {
            res += "Codigo: " + aux.dato.codigo
                    + " | Descripcion: " + aux.dato.descripcion
                    + " | Costo: " + aux.dato.costo
                    + " | Ingreso: " + aux.dato.fechaIngreso
                    + " | Entrega: " + aux.dato.fechaEntrega
                    + " | Estado: " + aux.dato.estado + "\n";
            aux = aux.sig;
        }

        return res;
    }

    public String mostrarPendientes() {
        NodoTrabajo aux = cabeza;
        String res = "";

        while (aux != null) {
            if (aux.dato.estado.equalsIgnoreCase("Pendiente")) {
                res += "Codigo: " + aux.dato.codigo
                        + " | Descripcion: " + aux.dato.descripcion
                        + " | Fecha entrega: " + aux.dato.fechaEntrega + "\n";
            }
            aux = aux.sig;
        }

        if (res.equals("")) {
            res = "No hay trabajos pendientes.";
        }

        return res;
    }

    public String mostrarPorFechaEntrega(String fecha) {
        NodoTrabajo aux = cabeza;
        String res = "";

        while (aux != null) {
            if (aux.dato.fechaEntrega.equals(fecha)) {
                res += "Codigo: " + aux.dato.codigo
                        + " | Descripcion: " + aux.dato.descripcion
                        + " | Estado: " + aux.dato.estado + "\n";
            }
            aux = aux.sig;
        }

        if (res.equals("")) {
            res = "No hay trabajos para esa fecha ingresada.";
        }

        return res;
    }

    //suma los costos de los trabajos finalizados, por lo que recorre la lista sumando los que cumplen la condicion
    public double calcularTotalFinalizados() {
        double total = 0;
        NodoTrabajo aux = cabeza;

        while (aux != null) {
            if (aux.dato.estado.equalsIgnoreCase("Trabajo finalizado")) {
                total += aux.dato.costo;
            }
            aux = aux.sig;
        }

        return total;
    }

    public void marcarEntregados() {
        NodoTrabajo aux = cabeza;
        while (aux != null) {
            if (aux.dato.estado.equalsIgnoreCase("Finalizado")) {
                aux.dato.estado = "Entregado";
            }
            aux = aux.sig;
        }
    }
}
//las clases siguientes sigen el mismo patron que trabajo
class Vehiculo {
    String placa;
    String marca;
    String modelo;
    int anio;
    ListaTrabajos trabajos;

    public Vehiculo(String placa, String marca, String modelo, int anio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.trabajos = new ListaTrabajos();
    }
}

class NodoVehiculo {
    Vehiculo dato;
    NodoVehiculo sig;

    public NodoVehiculo(Vehiculo dato) {
        this.dato = dato;
        this.sig = null;
    }
}

class ListaVehiculos {
    NodoVehiculo cabeza;

    public ListaVehiculos() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void insertar(Vehiculo v) {
        NodoVehiculo nuevo = new NodoVehiculo(v);

        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoVehiculo aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    public Vehiculo buscarPorPlaca(String placa) {
        NodoVehiculo aux = cabeza;
        while (aux != null) {
            if (aux.dato.placa.equalsIgnoreCase(placa)) {
                return aux.dato;
            }
            aux = aux.sig;
        }
        return null;
    }

    public String mostrarTodos() {
        NodoVehiculo aux = cabeza;
        String res = "";

        if (aux == null) {
            return "No hay vehiculos registrados.";
        }

        while (aux != null) {
            res += "Placa: " + aux.dato.placa
                    + " | Marca: " + aux.dato.marca
                    + " | Modelo: " + aux.dato.modelo
                    + " | Año: " + aux.dato.anio + "\n";
            aux = aux.sig;
        }

        return res;
    }
}

class Cliente {
    String cedula;
    String nombre;
    String telefono;
    ListaVehiculos vehiculos;

    public Cliente(String cedula, String nombre, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.vehiculos = new ListaVehiculos();
    }
}

class NodoCliente {
    Cliente dato;
    NodoCliente sig;

    public NodoCliente(Cliente dato) {
        this.dato = dato;
        this.sig = null;
    }
}

class ListaClientes {
    NodoCliente cabeza;

    public ListaClientes() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void insertar(Cliente c) {
        NodoCliente nuevo = new NodoCliente(c);

        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoCliente aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    public Cliente buscarPorCedula(String cedula) {
        NodoCliente aux = cabeza;
        while (aux != null) {
            if (aux.dato.cedula.equalsIgnoreCase(cedula)) {
                return aux.dato;
            }
            aux = aux.sig;
        }
        return null;
    }

    public Vehiculo buscarVehiculoGlobal(String placa) {
        NodoCliente auxCliente = cabeza;
        while (auxCliente != null) {
            Vehiculo v = auxCliente.dato.vehiculos.buscarPorPlaca(placa);
            if (v != null) {
                return v;
            }
            auxCliente = auxCliente.sig;
        }
        return null;
    }

    public Cliente buscarClientePorPlaca(String placa) {
        NodoCliente auxCliente = cabeza;
        while (auxCliente != null) {
            Vehiculo v = auxCliente.dato.vehiculos.buscarPorPlaca(placa);
            if (v != null) {
                return auxCliente.dato;
            }
            auxCliente = auxCliente.sig;
        }
        return null;
    }

    public String mostrarClientes() {
        NodoCliente aux = cabeza;
        String res = "";

        if (aux == null) {
            return "No hay clientes registrados.\n";
        }

        while (aux != null) {
            res += "Cedula: " + aux.dato.cedula
                    + " | Nombre: " + aux.dato.nombre
                    + " | Telefono: " + aux.dato.telefono + "\n";
            aux = aux.sig;
        }

        return res;
    }

    public String mostrarTrabajosPorCliente(String cedula) {
        Cliente c = buscarPorCedula(cedula);

        if (c == null) {
            return "Cliente no encontrado.\n";
        }

        String res = "Cliente: " + c.nombre + "\n";
        NodoVehiculo auxVeh = c.vehiculos.cabeza;

        if (auxVeh == null) {
            return res + "Este cliente no tiene vehiculos.\n";
        }

        while (auxVeh != null) {
            res += "Vehiculo: " + auxVeh.dato.placa + "\n";
            res += auxVeh.dato.trabajos.mostrarTodos();
            auxVeh = auxVeh.sig;
        }

        return res;
    }

    public String mostrarPendientes() {
        NodoCliente auxCli = cabeza;
        String res = "";

        while (auxCli != null) {
            NodoVehiculo auxVeh = auxCli.dato.vehiculos.cabeza;
            while (auxVeh != null) {
                NodoTrabajo auxTra = auxVeh.dato.trabajos.cabeza;
                while (auxTra != null) {
                    if (auxTra.dato.estado.equalsIgnoreCase("Pendiente")) {
                        res += "Cliente: " + auxCli.dato.nombre
                                + " | Placa: " + auxVeh.dato.placa
                                + " | Trabajo: " + auxTra.dato.descripcion
                                + " | Fecha entrega: " + auxTra.dato.fechaEntrega + "\n";
                    }
                    auxTra = auxTra.sig;
                }
                auxVeh = auxVeh.sig;
            }
            auxCli = auxCli.sig;
        }

        if (res.equals("")) {
            res = "No hay trabajos pendientes.\n";
        }

        return res;
    }

    public String mostrarPorFechaEntrega(String fecha) {
        NodoCliente auxCli = cabeza;
        String res = "";

        while (auxCli != null) {
            NodoVehiculo auxVeh = auxCli.dato.vehiculos.cabeza;
            while (auxVeh != null) {
                NodoTrabajo auxTra = auxVeh.dato.trabajos.cabeza;
                while (auxTra != null) {
                    if (auxTra.dato.fechaEntrega.equals(fecha)) {
                        res += "Cliente: " + auxCli.dato.nombre
                                + " | Placa: " + auxVeh.dato.placa
                                + " | Trabajo: " + auxTra.dato.descripcion
                                + " | Estado: " + auxTra.dato.estado + "\n";
                    }
                    auxTra = auxTra.sig;
                }
                auxVeh = auxVeh.sig;
            }
            auxCli = auxCli.sig;
        }

        if (res.equals("")) {
            res = "No hay trabajos para esa fecha.\n";
        }

        return res;
    }
}

class Factura {
    int numero;
    String cedulaCliente;
    String nombreCliente;
    String placa;
    double total;
    String fecha;
    String metodoPago;

    public Factura(int numero, String cedulaCliente, String nombreCliente,
                   String placa, double total, String fecha, String metodoPago) {
        this.numero = numero;
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.placa = placa;
        this.total = total;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
    }
}

class NodoFactura {
    Factura dato;
    NodoFactura sig;

    public NodoFactura(Factura dato) {
        this.dato = dato;
        this.sig = null;
    }
}

class ListaFacturas {
    NodoFactura cabeza;

    public ListaFacturas() {
        cabeza = null;
    }

    public void insertar(Factura f) {
        NodoFactura nuevo = new NodoFactura(f);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoFactura aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    public String mostrarFacturas() {
        NodoFactura aux = cabeza;
        String res = "";

        if (aux == null) {
            return "No hay facturas.\n";
        }

        while (aux != null) {
            res += "Factura: " + aux.dato.numero
                    + " | Cliente: " + aux.dato.nombreCliente
                    + " | Placa: " + aux.dato.placa
                    + " | Total: " + aux.dato.total
                    + " | Fecha: " + aux.dato.fecha
                    + " | Metodo: " + aux.dato.metodoPago + "\n";
            aux = aux.sig;
        }

        return res;
    }

    public double recaudadoPorDia(String fecha) {
        double total = 0;
        NodoFactura aux = cabeza;

        while (aux != null) {
            if (aux.dato.fecha.equals(fecha)) {
                total += aux.dato.total;
            }
            aux = aux.sig;
        }

        return total;
    }

    //Se genera la factura y se guarda en un txt
    public void guardarFacturaTxt(Factura f) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("factura_" + f.numero + ".txt"));
            pw.println("===== FACTURA =====");
            pw.println("Numero: " + f.numero);
            pw.println("Cliente: " + f.nombreCliente);
            pw.println("Cedula: " + f.cedulaCliente);
            pw.println("Placa: " + f.placa);
            pw.println("Fecha: " + f.fecha);
            pw.println("Metodo de pago: " + f.metodoPago);
            pw.println("Total: " + f.total);
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar factura.");
        }
    }

    //genera el voucher solo si el pago se realiza con tarjeta
    public void guardarVoucherTxt(Factura f) {

        //validacion
        if (!f.metodoPago.equalsIgnoreCase("Tarjeta")) {
            return;
        }

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("boucher" + f.numero + ".txt"));
            pw.println("===== VOUCHER =====");
            pw.println("Factura: " + f.numero);
            pw.println("Cliente: " + f.nombreCliente);
            pw.println("Placa: " + f.placa);
            pw.println("Fecha: " + f.fecha);
            pw.println("Monto: " + f.total);
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar voucher.");
        }
    }
}

//Parte de la interfaz gráfica
public class TallerAutosGUI extends JFrame implements ActionListener {

    static ListaClientes clientes = new ListaClientes();
    static ListaFacturas facturas = new ListaFacturas();
    static int consecutivoFactura = 1;

    //Botones
    JButton btnCliente, btnVehiculo, btnTrabajo, btnVehiculoConsulta;
    JButton btnClienteConsulta, btnFacturas, btnPendientes, btnFecha;
    JButton btnRecaudado, btnFacturar, btnClientes, btnVehiculos, btnSalir;

    JTextArea area;
    JScrollPane scroll;

    public TallerAutosGUI() {
        setTitle("Taller de Autos");
        setSize(900, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //texto en los botones
        btnCliente = new JButton("Registrar cliente");
        btnVehiculo = new JButton("Registrar vehiculo");
        btnTrabajo = new JButton("Registrar trabajo");
        btnVehiculoConsulta = new JButton("Trabajos por vehiculo");
        btnClienteConsulta = new JButton("Trabajos por cliente");
        btnFacturas = new JButton("Consultar facturas");
        btnPendientes = new JButton("Trabajos pendientes");
        btnFecha = new JButton("Trabajos por fecha");
        btnRecaudado = new JButton("Recaudado por dia");
        btnFacturar = new JButton("Facturar");
        btnClientes = new JButton("Ver clientes");
        btnVehiculos = new JButton("Ver vehiculos por cliente");
        btnSalir = new JButton("Salir");

        //posicion y tamaño de los botones
        btnCliente.setBounds(20, 20, 180, 30);
        btnVehiculo.setBounds(220, 20, 180, 30);
        btnTrabajo.setBounds(420, 20, 180, 30);
        btnFacturar.setBounds(620, 20, 180, 30);
        btnVehiculoConsulta.setBounds(20, 70, 180, 30);
        btnClienteConsulta.setBounds(220, 70, 180, 30);
        btnFacturas.setBounds(420, 70, 180, 30);
        btnPendientes.setBounds(620, 70, 180, 30);
        btnFecha.setBounds(20, 120, 180, 30);
        btnRecaudado.setBounds(220, 120, 180, 30);
        btnClientes.setBounds(420, 120, 180, 30);
        btnVehiculos.setBounds(620, 120, 180, 30);
        btnSalir.setBounds(350, 500, 180, 35);

        //agrega componetes en la interfaz para que sean visibles
        add(btnCliente);
        add(btnVehiculo);
        add(btnTrabajo);
        add(btnFacturar);
        add(btnVehiculoConsulta);
        add(btnClienteConsulta);
        add(btnFacturas);
        add(btnPendientes);
        add(btnFecha);
        add(btnRecaudado);
        add(btnClientes);
        add(btnVehiculos);
        add(btnSalir);

        btnCliente.addActionListener(this);
        btnVehiculo.addActionListener(this);
        btnTrabajo.addActionListener(this);
        btnFacturar.addActionListener(this);
        btnVehiculoConsulta.addActionListener(this);
        btnClienteConsulta.addActionListener(this);
        btnFacturas.addActionListener(this);
        btnPendientes.addActionListener(this);
        btnFecha.addActionListener(this);
        btnRecaudado.addActionListener(this);
        btnClientes.addActionListener(this);
        btnVehiculos.addActionListener(this);
        btnSalir.addActionListener(this);

        area = new JTextArea();
        area.setEditable(false);
        scroll = new JScrollPane(area);
        scroll.setBounds(20, 180, 820, 290);
        add(scroll);
    }

    public void mostrarEnArea(String texto) {
        area.setText(texto);
    }

    /*
    ****
    Validaciones
    ****
    */
    public void registrarCliente() {
        String cedula = JOptionPane.showInputDialog(this, "Cedula:");
        if (cedula == null || cedula.trim().equals("")) return;

        if (clientes.buscarPorCedula(cedula) != null) {
            JOptionPane.showMessageDialog(this, "La cedula ingresada ya existe.");
            return;
        }

        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        if (nombre == null || nombre.trim().equals("")) return;

        String telefono = JOptionPane.showInputDialog(this, "Telefono:");
        if (telefono == null || telefono.trim().equals("")) return;

        Cliente c = new Cliente(cedula, nombre, telefono);
        clientes.insertar(c);

        JOptionPane.showMessageDialog(this, "Cliente registrado.");
    }

    public void registrarVehiculo() {
        String cedula = JOptionPane.showInputDialog(this, "Cedula del cliente:");
        if (cedula == null || cedula.trim().equals("")) return;

        Cliente c = clientes.buscarPorCedula(cedula);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            return;
        }

        String placa = JOptionPane.showInputDialog(this, "Placa:");
        if (placa == null || placa.trim().equals("")) return;

        if (clientes.buscarVehiculoGlobal(placa) != null) {
            JOptionPane.showMessageDialog(this, "La placa ingresada ya existe.");
            return;
        }

        String marca = JOptionPane.showInputDialog(this, "Marca:");
        if (marca == null || marca.trim().equals("")) return;

        String modelo = JOptionPane.showInputDialog(this, "Modelo:");
        if (modelo == null || modelo.trim().equals("")) return;

        String anioTexto = JOptionPane.showInputDialog(this, "Anio:");
        if (anioTexto == null || anioTexto.trim().equals("")) return;

        int anio;
        try {
            anio = Integer.parseInt(anioTexto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Año invalido.");
            return;
        }

        Vehiculo v = new Vehiculo(placa, marca, modelo, anio);
        c.vehiculos.insertar(v);

        JOptionPane.showMessageDialog(this, "Vehiculo registrado.");
    }

    public void registrarTrabajo() {
        String placa = JOptionPane.showInputDialog(this, "Placa del vehiculo:");
        if (placa == null || placa.trim().equals("")) return;

        Vehiculo v = clientes.buscarVehiculoGlobal(placa);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "Vehiculo no encontrado.");
            return;
        }

        String codigoTexto = JOptionPane.showInputDialog(this, "Codigo del trabajo:");
        if (codigoTexto == null || codigoTexto.trim().equals("")) return;

        int codigo;
        try {
            codigo = Integer.parseInt(codigoTexto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Codigo invalido.");
            return;
        }

        if (v.trabajos.buscarPorCodigo(codigo) != null) {
            JOptionPane.showMessageDialog(this, "El codigo ya existe en ese vehiculo.");
            return;
        }

        String descripcion = JOptionPane.showInputDialog(this, "Descripcion:");
        if (descripcion == null || descripcion.trim().equals("")) return;

        String costoTexto = JOptionPane.showInputDialog(this, "Costo:");
        if (costoTexto == null || costoTexto.trim().equals("")) return;

        double costo;
        try {
            costo = Double.parseDouble(costoTexto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Costo invalido.");
            return;
        }

        if (costo <= 0) {
            JOptionPane.showMessageDialog(this, "El costo debe ser mayor que 0.");
            return;
        }

        String fechaIngreso = JOptionPane.showInputDialog(this, "Fecha ingreso (dd-mm-yyyy):");
        if (fechaIngreso == null || fechaIngreso.trim().equals("")) return;

        String fechaEntrega = JOptionPane.showInputDialog(this, "Fecha entrega (dd-mm-yyyy):");
        if (fechaEntrega == null || fechaEntrega.trim().equals("")) return;

        String estado = JOptionPane.showInputDialog(this, "Estado (Pendiente/Finalizado/Entregado):");
        if (estado == null || estado.trim().equals("")) return;

        if (!estado.equalsIgnoreCase("Pendiente")
                && !estado.equalsIgnoreCase("Finalizado")
                && !estado.equalsIgnoreCase("Entregado")) {
            JOptionPane.showMessageDialog(this, "Estado invalido.");
            return;
        }

        Trabajo t = new Trabajo(codigo, descripcion, costo, fechaIngreso, fechaEntrega, estado);
        v.trabajos.insertar(t);

        JOptionPane.showMessageDialog(this, "Trabajo registrado.");
    }

    public void consultarTrabajosVehiculo() {
        String placa = JOptionPane.showInputDialog(this, "Placa:");
        if (placa == null || placa.trim().equals("")) return;

        Vehiculo v = clientes.buscarVehiculoGlobal(placa);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "Vehiculo no encontrado.");
            return;
        }

        mostrarEnArea("Trabajos del vehiculo " + placa + "\n" + v.trabajos.mostrarTodos());
    }

    public void consultarTrabajosCliente() {
        String cedula = JOptionPane.showInputDialog(this, "Cedula del cliente:");
        if (cedula == null || cedula.trim().equals("")) return;

        mostrarEnArea(clientes.mostrarTrabajosPorCliente(cedula));
    }

    public void consultarFacturas() {
        mostrarEnArea(facturas.mostrarFacturas());
    }

    public void consultarPendientes() {
        mostrarEnArea(clientes.mostrarPendientes());
    }

    public void consultarPorFecha() {
        String fecha = JOptionPane.showInputDialog(this, "Fecha entrega (dd-mm-yyyy):");
        if (fecha == null || fecha.trim().equals("")) return;

        mostrarEnArea(clientes.mostrarPorFechaEntrega(fecha));
    }

    public void consultarRecaudado() {
        String fecha = JOptionPane.showInputDialog(this, "Fecha (dd-mm-yyyy):");
        if (fecha == null || fecha.trim().equals("")) return;

        mostrarEnArea("Total recaudado en " + fecha + ": " + facturas.recaudadoPorDia(fecha));
    }

    public void mostrarClientes() {
        mostrarEnArea(clientes.mostrarClientes());
    }

    public void mostrarVehiculosCliente() {
        String cedula = JOptionPane.showInputDialog(this, "Cedula del cliente:");
        if (cedula == null || cedula.trim().equals("")) return;

        Cliente c = clientes.buscarPorCedula(cedula);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            return;
        }

        mostrarEnArea("Vehiculos de " + c.nombre + "\n" + c.vehiculos.mostrarTodos());
    }

    public void facturar() {
        String placa = JOptionPane.showInputDialog(this, "Placa del vehiculo:");
        if (placa == null || placa.trim().equals("")) return;

        Vehiculo v = clientes.buscarVehiculoGlobal(placa);
        Cliente c = clientes.buscarClientePorPlaca(placa);

        if (v == null || c == null) {
            JOptionPane.showMessageDialog(this, "Vehiculo no encontrado.");
            return;
        }

        double total = v.trabajos.calcularTotalFinalizados();

        if (total <= 0) {
            JOptionPane.showMessageDialog(this, "No hay trabajos finalizados para facturar.");
            return;
        }

        String fecha = JOptionPane.showInputDialog(this, "Fecha factura (dd-mm-yyyy):");
        if (fecha == null || fecha.trim().equals("")) return;

        String metodo = JOptionPane.showInputDialog(this, "Metodo de pago (Efectivo/Tarjeta/Cheque):");
        if (metodo == null || metodo.trim().equals("")) return;

        if (!metodo.equalsIgnoreCase("Efectivo")
                && !metodo.equalsIgnoreCase("Tarjeta")
                && !metodo.equalsIgnoreCase("Cheque")) {
            JOptionPane.showMessageDialog(this, "Metodo de pago invalido.");
            return;
        }

        Factura f = new Factura(consecutivoFactura, c.cedula, c.nombre, placa, total, fecha, metodo);
        facturas.insertar(f);
        facturas.guardarFacturaTxt(f);
        facturas.guardarVoucherTxt(f);
        v.trabajos.marcarEntregados();
        consecutivoFactura++;

        JOptionPane.showMessageDialog(this,
                "Factura generada correctamente.\n" +
                "Numero: " + f.numero + "\n" +
                "Cliente: " + f.nombreCliente + "\n" +
                "Total: " + f.total);
    }

    //Maneja los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCliente) registrarCliente();
        if (e.getSource() == btnVehiculo) registrarVehiculo();
        if (e.getSource() == btnTrabajo) registrarTrabajo();
        if (e.getSource() == btnVehiculoConsulta) consultarTrabajosVehiculo();
        if (e.getSource() == btnClienteConsulta) consultarTrabajosCliente();
        if (e.getSource() == btnFacturas) consultarFacturas();
        if (e.getSource() == btnPendientes) consultarPendientes();
        if (e.getSource() == btnFecha) consultarPorFecha();
        if (e.getSource() == btnRecaudado) consultarRecaudado();
        if (e.getSource() == btnFacturar) facturar();
        if (e.getSource() == btnClientes) mostrarClientes();
        if (e.getSource() == btnVehiculos) mostrarVehiculosCliente();
        if (e.getSource() == btnSalir) System.exit(0);
    }

    public static void main(String[] args) {
        TallerAutosGUI ventana = new TallerAutosGUI();
        ventana.setVisible(true);
    }
}