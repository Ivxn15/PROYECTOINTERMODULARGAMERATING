namespace ProyectoIntermodular
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            //Con esto si arrastro la ventana casi no se laguea
            this.DoubleBuffered = true;
            
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // Creacion de la ventana 
            this.Size = new Size(1920, 1080);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.FormBorderStyle = FormBorderStyle.Sizable;
            this.ControlBox = true;

            // Creacion del div donde va el inicio de sesion
            Panel div = new Panel();
            div.Size = new Size(400, 400); 
            div.BackColor = Color.FromArgb(45, 45, 45);
            div.Left = (this.ClientSize.Width - div.Width) / 2;
            div.Top = (this.ClientSize.Height - div.Height) / 2;
            this.Controls.Add(div);

            // borrar del forms y anadir al div
            this.Controls.Remove(txtCorreo);
            this.Controls.Remove(txtPass);
            this.Controls.Remove(btnLogin);
            div.Controls.Add(txtCorreo);
            div.Controls.Add(txtPass);
            div.Controls.Add(btnLogin);

            // Estilos titulo del div
            Label lblTitulo = new Label();
            lblTitulo.Text = "Inicio de Sesión";
            lblTitulo.Font = new Font("JetBrains Mono", 20, FontStyle.Bold);
            lblTitulo.ForeColor = Color.White;
            lblTitulo.AutoSize = false;
            lblTitulo.TextAlign = ContentAlignment.MiddleCenter;
            lblTitulo.Size = new Size(div.Width, 50);
            lblTitulo.Location = new Point(0, 10);
            div.Controls.Add(lblTitulo);

            // Estilo de textbox de correo electronico para iniciar sesion
            txtCorreo.BorderStyle = BorderStyle.None;
            txtCorreo.BackColor = Color.FromArgb(45, 45, 45);
            txtCorreo.ForeColor = Color.Gray;
            txtCorreo.Font = new Font("JetBrains Mono", 14);
            txtCorreo.Text = "Correo";

            txtCorreo.Enter += (s, ev) =>
            {
                if (txtCorreo.Text == "Correo")
                {
                    txtCorreo.Text = "";
                    txtCorreo.ForeColor = Color.White;
                }
            };

            txtCorreo.Leave += (s, ev) =>
            {
                if (string.IsNullOrWhiteSpace(txtCorreo.Text))
                {
                    txtCorreo.Text = "Correo";
                    txtCorreo.ForeColor = Color.Gray;
                }
            };

            txtCorreo.Width = 250;
            txtCorreo.Left = (div.Width - txtCorreo.Width) / 2;
            txtCorreo.Top = lblTitulo.Bottom + 20;

            Panel lineCorreo = new Panel();
            lineCorreo.BackColor = Color.Gray;
            lineCorreo.Width = txtCorreo.Width;
            lineCorreo.Height = 2;
            lineCorreo.Left = txtCorreo.Left;
            lineCorreo.Top = txtCorreo.Bottom + 2;
            div.Controls.Add(lineCorreo);

            // Estilo textbox de la contrasena
            txtPass.BorderStyle = BorderStyle.None;
            txtPass.BackColor = Color.FromArgb(45, 45, 45);
            txtPass.ForeColor = Color.Gray;
            txtPass.Font = new Font("JetBrains Mono", 14);
            txtPass.Text = "Contraseña";

            txtPass.Enter += (s, ev) =>
            {
                if (txtPass.Text == "Contraseña")
                {
                    txtPass.Text = "";
                    txtPass.ForeColor = Color.White;
                    txtPass.UseSystemPasswordChar = true;
                }
            };

            txtPass.Leave += (s, ev) =>
            {
                if (string.IsNullOrWhiteSpace(txtPass.Text))
                {
                    txtPass.UseSystemPasswordChar = false;
                    txtPass.Text = "Contraseña";
                    txtPass.ForeColor = Color.Gray;
                }
            };

            txtPass.Width = 250;
            txtPass.Left = (div.Width - txtPass.Width) / 2;
            txtPass.Top = lineCorreo.Bottom + 20;

            Panel linePass = new Panel();
            linePass.BackColor = Color.Gray;
            linePass.Width = txtPass.Width;
            linePass.Height = 2;
            linePass.Left = txtPass.Left;
            linePass.Top = txtPass.Bottom + 2;
            div.Controls.Add(linePass);

            //Estilos boton de Inicio de sesion 
            btnLogin.FlatStyle = FlatStyle.Flat;
            btnLogin.FlatAppearance.BorderSize = 0;
            btnLogin.BackColor = Color.FromArgb(0, 120, 215);
            btnLogin.ForeColor = Color.White;
            btnLogin.Font = new Font("JetBrains Mono", 12, FontStyle.Bold);
            btnLogin.Cursor = Cursors.Hand;

            btnLogin.MouseEnter += (s, ev) => { btnLogin.BackColor = Color.FromArgb(0, 150, 255); };
            btnLogin.MouseLeave += (s, ev) => { btnLogin.BackColor = Color.FromArgb(0, 120, 215); };

            btnLogin.Width = 250;
            btnLogin.Left = (div.Width - btnLogin.Width) / 2;
            btnLogin.Top = linePass.Bottom + 20;

            // Etiqueta de si no tienes cuenta
            Label lblNoCuenta = new Label();
            lblNoCuenta.Text = "¿No tienes cuenta?";
            lblNoCuenta.Font = new Font("JetBrains Mono", 10, FontStyle.Regular);
            lblNoCuenta.ForeColor = Color.Gray;
            lblNoCuenta.AutoSize = false;
            lblNoCuenta.TextAlign = ContentAlignment.MiddleCenter;
            lblNoCuenta.Size = new Size(div.Width, 20);
            lblNoCuenta.Location = new Point(0, btnLogin.Bottom + 15);
            div.Controls.Add(lblNoCuenta);

            //Estilos del bootn para crear Cuenta
  
            btnCrearCuenta.FlatStyle = FlatStyle.Flat;
            btnCrearCuenta.FlatAppearance.BorderSize = 0;
            btnCrearCuenta.BackColor = Color.FromArgb(0, 180, 0);
            btnCrearCuenta.ForeColor = Color.White;
            btnCrearCuenta.Font = new Font("JetBrains Mono", 10, FontStyle.Bold);
            btnCrearCuenta.Cursor = Cursors.Hand;

            btnCrearCuenta.MouseEnter += (s, ev) => { btnCrearCuenta.BackColor = Color.FromArgb(0, 220, 0); };
            btnCrearCuenta.MouseLeave += (s, ev) => { btnCrearCuenta.BackColor = Color.FromArgb(0, 180, 0); };

            btnCrearCuenta.Width = 120;
            btnCrearCuenta.Left = (div.Width - btnCrearCuenta.Width) / 2;
            btnCrearCuenta.Top = lblNoCuenta.Bottom + 5;
            div.Controls.Add(btnCrearCuenta);

            btnCrearCuenta.Click += (s, ev) =>
            {
                
                CrearCuentaForm crearForm = new CrearCuentaForm();
                crearForm.Show();
                this.Hide(); 
            };

            // Implemento el fondo y el icono a la aplicacion 
            this.BackgroundImage = Properties.Resources.ow;
            this.BackgroundImageLayout = ImageLayout.Stretch;
            this.Icon = Properties.Resources.logo;
            this.Text = "Gamerating";


        }

        private async void btnLogin_Click(object sender, EventArgs e)
        {
            try
            {
                if (txtCorreo.Text == "Correo" || txtPass.Text == "Contraseña")
                {
                    MessageBox.Show("Introduce correo y contraseña");
                    return;
                }

                ApiUsuarios api = new ApiUsuarios();

                int id = await api.Login(txtCorreo.Text, txtPass.Text);

                
                Sesion.UsuarioId = id;

                MessageBox.Show("Login correcto");

                FormPrincipal principal = new FormPrincipal();
                principal.Show();
                this.Hide();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error:\n" + ex.Message);
            }
        }

    }
}
