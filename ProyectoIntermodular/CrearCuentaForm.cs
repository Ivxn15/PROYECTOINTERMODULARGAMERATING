using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics.Eventing.Reader;
using System.Drawing;
using System.Linq;
using System.Net.Http.Json;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ProyectoIntermodular
{
    public partial class CrearCuentaForm : Form
    {
        public CrearCuentaForm()
        {
            InitializeComponent();
            //Con esto si arrastro la ventana casi no se laguea
            this.DoubleBuffered = true;
        }

        private void CrearCuentaForm_Load(object sender, EventArgs e)
        {
            //Para cerrar el programa cuando cierre la ventana
            this.FormClosing += (s, e) => Application.Exit();
            //Creacion de la ventana
            this.Size = new Size(1920, 1080);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.FormBorderStyle = FormBorderStyle.Sizable;
            this.ControlBox = true;

            // Creación del div
            Panel div = new Panel();
            div.Size = new Size(500, 500);
            div.BackColor = Color.FromArgb(45, 45, 45);
            div.Left = (this.ClientSize.Width - div.Width) / 2;
            div.Top = (this.ClientSize.Height - div.Height) / 2;
            this.Controls.Add(div);

            // Estilos etiqueta de crear cuenta
            Label lblTitulo = new Label();
            lblTitulo.Text = "Crear cuenta";
            lblTitulo.Font = new Font("JetBrains Mono", 20, FontStyle.Bold);
            lblTitulo.ForeColor = Color.White;
            lblTitulo.AutoSize = true;
            lblTitulo.Left = (div.Width - TextRenderer.MeasureText(lblTitulo.Text, lblTitulo.Font).Width) / 2;
            lblTitulo.Top = 20;
            div.Controls.Add(lblTitulo);

            // Placeholders de los textboxes
            TextBox[] textboxes = { txtNombre, txtCorreo, txtPass, txtConfirmPass };
            string[] placeholders = { "Nombre", "Correo", "Contrasena", "Confirmar Contraseña" };

            for (int i = 0; i < textboxes.Length; i++)
            {
                var txt = textboxes[i];
                var placeholder = placeholders[i];

                this.Controls.Remove(txt);
                div.Controls.Add(txt);

                txt.BorderStyle = BorderStyle.None;
                txt.BackColor = Color.FromArgb(45, 45, 45);
                txt.ForeColor = Color.Gray;
                txt.Font = new Font("JetBrains Mono", 14);
                txt.Text = placeholder;
                txt.Width = 300;
                txt.Left = (div.Width - txt.Width) / 2;
                txt.Top = 80 + i * 70;

                txt.Enter += (s, ev) =>
                {
                    if (txt.Text == placeholder)
                    {
                        txt.Text = "";
                        txt.ForeColor = Color.White;
                        if (txt == txtPass || txt == txtConfirmPass)
                            txt.UseSystemPasswordChar = true;
                    }
                };

                txt.Leave += (s, ev) =>
                {
                    if (string.IsNullOrWhiteSpace(txt.Text))
                    {
                        txt.Text = placeholder;
                        txt.ForeColor = Color.Gray;
                        if (txt == txtPass || txt == txtConfirmPass)
                            txt.UseSystemPasswordChar = false;
                    }
                };

                
                Panel linea = new Panel();
                linea.BackColor = Color.Gray;
                linea.Width = txt.Width;
                linea.Height = 2;
                linea.Left = txt.Left;
                linea.Top = txt.Bottom + 2;
                div.Controls.Add(linea);
            }

            // Estilos del botón de crear cuenta
            this.Controls.Remove(btnCrearCuenta);
            div.Controls.Add(btnCrearCuenta);

            btnCrearCuenta.FlatStyle = FlatStyle.Flat;
            btnCrearCuenta.FlatAppearance.BorderSize = 0;
            btnCrearCuenta.BackColor = Color.FromArgb(0, 120, 215);
            btnCrearCuenta.ForeColor = Color.White;
            btnCrearCuenta.Font = new Font("JetBrains Mono", 12, FontStyle.Bold);
            btnCrearCuenta.Cursor = Cursors.Hand;
            btnCrearCuenta.Width = 300;
            btnCrearCuenta.Left = (div.Width - btnCrearCuenta.Width) / 2;
            btnCrearCuenta.Top = 80 + textboxes.Length * 70;

            btnCrearCuenta.MouseEnter += (s, ev) => btnCrearCuenta.BackColor = Color.FromArgb(0, 150, 255);
            btnCrearCuenta.MouseLeave += (s, ev) => btnCrearCuenta.BackColor = Color.FromArgb(0, 120, 215);

            btnCrearCuenta.Click += async (s, ev) =>
            {
                try
                {
                    
                    if (txtNombre.Text == "Nombre" || string.IsNullOrWhiteSpace(txtNombre.Text))
                    {
                        MessageBox.Show("Debes ingresar un nombre de usuario", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    if (txtCorreo.Text == "Correo" || string.IsNullOrWhiteSpace(txtCorreo.Text) || !txtCorreo.Text.Contains("@"))
                    {
                        MessageBox.Show("Debes ingresar un correo válido", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    if (txtPass.Text == "Contraseña" || string.IsNullOrWhiteSpace(txtPass.Text))
                    {
                        MessageBox.Show("Debes ingresar una contrasena", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    if (txtPass.Text != txtConfirmPass.Text)
                    {
                        MessageBox.Show("Las contraseñas no coinciden", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    
                    ApiUsuarios api = new ApiUsuarios();
                    int id = await api.Registrar(txtNombre.Text, txtCorreo.Text, txtPass.Text);

                    
                    Sesion.UsuarioId = id;
                    Sesion.Nombre = txtNombre.Text; 

                    MessageBox.Show("Cuenta creada y logueada :)", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);

                    // Abrir FormPrincipal con sesión iniciada
                    FormPrincipal principal = new FormPrincipal();
                    principal.Show();
                    this.Hide();
                }
                catch (Exception ex)
                {
                    if (ex.Message.Contains("usuario"))
                        MessageBox.Show("El nombre de usuario ya existe", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    else
                        MessageBox.Show("Error al crear cuenta:\n" + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            };

            // Boton para volver
            btnVolver.Text = "Volver a Inicio";
            btnVolver.FlatStyle = FlatStyle.Flat;
            btnVolver.FlatAppearance.BorderSize = 0;
            btnVolver.BackColor = Color.Gray;
            btnVolver.ForeColor = Color.White;
            btnVolver.Font = new Font("JetBrains Mono", 12, FontStyle.Bold);
            btnVolver.Width = 200;
            btnVolver.Left = (div.Width - btnVolver.Width) / 2;
            btnVolver.Top = btnCrearCuenta.Bottom + 20;
            btnVolver.Cursor = Cursors.Hand;

            btnVolver.MouseEnter += (s, ev) => btnVolver.BackColor = Color.DarkGray;
            btnVolver.MouseLeave += (s, ev) => btnVolver.BackColor = Color.Gray;

            btnVolver.Click += (s, ev) =>
            {
                
                Form1 inicio = new Form1();
                inicio.Show();
                this.Hide();
            };

            div.Controls.Add(btnVolver);

            
            this.BackgroundImage = Properties.Resources.sh2;
            this.BackgroundImageLayout = ImageLayout.Stretch;
            this.Icon = Properties.Resources.logo;
            this.Text = "Gamerating";
        }
    }
}
