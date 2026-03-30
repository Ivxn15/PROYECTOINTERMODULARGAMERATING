using System;
using System.Drawing;
using System.Text;
using System.Text.Json;
using System.Windows.Forms;

namespace ProyectoIntermodular
{
    public partial class CrearResenaForm : Form
    {
        public CrearResenaForm()
        {
            InitializeComponent();
            this.DoubleBuffered = true;
        }

        private void CrearResenaForm_Load(object sender, EventArgs e)
        {
            //Estilo de la ventana
            this.Size = new Size(1920, 1080);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.FormClosing += (s, ev) => Application.Exit();
            //Estilo del panel principal
            Panel div = new Panel();
            div.Size = new Size(500, 500);
            div.BackColor = Color.FromArgb(45, 45, 45);
            div.Left = (this.ClientSize.Width - div.Width) / 2;
            div.Top = (this.ClientSize.Height - div.Height) / 2;
            this.Controls.Add(div);

            // Estilo del label del titulo del div
            Label lblTitulo = new Label();
            lblTitulo.Text = "Crear Resena";
            lblTitulo.Font = new Font("JetBrains Mono", 20, FontStyle.Bold);
            lblTitulo.ForeColor = Color.White;
            lblTitulo.Size = new Size(div.Width, 50);
            lblTitulo.TextAlign = ContentAlignment.MiddleCenter;
            lblTitulo.Top = 20;
            div.Controls.Add(lblTitulo);

            // Estilo del textbox del juego
            TextBox txtJuego = new TextBox();
            txtJuego.Width = 300;
            txtJuego.Left = (div.Width - txtJuego.Width) / 2;
            txtJuego.Top = lblTitulo.Bottom + 30;
            txtJuego.Text = "Juego";
            txtJuego.ForeColor = Color.Gray;
            txtJuego.BackColor = Color.FromArgb(45, 45, 45);
            txtJuego.BorderStyle = BorderStyle.None;

            txtJuego.Enter += (s, e) =>
            {
                if (txtJuego.Text == "Juego")
                {
                    txtJuego.Text = "";
                    txtJuego.ForeColor = Color.White;
                }
            };

            txtJuego.Leave += (s, e) =>
            {
                if (string.IsNullOrWhiteSpace(txtJuego.Text))
                {
                    txtJuego.Text = "Juego";
                    txtJuego.ForeColor = Color.Gray;
                }
            };

            div.Controls.Add(txtJuego);

            // Estilo del textbox de la puntuacion del juego
            TextBox txtPuntuacion = new TextBox();
            txtPuntuacion.Width = 300;
            txtPuntuacion.Left = txtJuego.Left;
            txtPuntuacion.Top = txtJuego.Bottom + 30;
            txtPuntuacion.Text = "Puntuacion (0-10)";
            txtPuntuacion.ForeColor = Color.Gray;
            txtPuntuacion.BackColor = Color.FromArgb(45, 45, 45);
            txtPuntuacion.BorderStyle = BorderStyle.None;

            txtPuntuacion.Enter += (s, e) =>
            {
                if (txtPuntuacion.Text.Contains("Puntuacion"))
                {
                    txtPuntuacion.Text = "";
                    txtPuntuacion.ForeColor = Color.White;
                }
            };

            txtPuntuacion.Leave += (s, e) =>
            {
                if (string.IsNullOrWhiteSpace(txtPuntuacion.Text))
                {
                    txtPuntuacion.Text = "Puntuacion (0-10)";
                    txtPuntuacion.ForeColor = Color.Gray;
                }
            };

            div.Controls.Add(txtPuntuacion);

            // Estilo del textbox del comentario de la resena
            TextBox txtComentario = new TextBox();
            txtComentario.Width = 300;
            txtComentario.Height = 80;
            txtComentario.Multiline = true;
            txtComentario.Left = txtJuego.Left;
            txtComentario.Top = txtPuntuacion.Bottom + 30;
            txtComentario.Text = "Comentario";
            txtComentario.ForeColor = Color.Gray;
            txtComentario.BackColor = Color.FromArgb(45, 45, 45);
            txtComentario.BorderStyle = BorderStyle.None;

            txtComentario.Enter += (s, e) =>
            {
                if (txtComentario.Text == "Comentario")
                {
                    txtComentario.Text = "";
                    txtComentario.ForeColor = Color.White;
                }
            };

            txtComentario.Leave += (s, e) =>
            {
                if (string.IsNullOrWhiteSpace(txtComentario.Text))
                {
                    txtComentario.Text = "Comentario";
                    txtComentario.ForeColor = Color.Gray;
                }
            };

            div.Controls.Add(txtComentario);

            // Estilo del boton de crear resena
            Button btnCrear = new Button();
            btnCrear.Text = "Crear Resena";
            btnCrear.Width = 200;
            btnCrear.Height = 40;
            btnCrear.Left = (div.Width - btnCrear.Width) / 2;
            btnCrear.Top = txtComentario.Bottom + 30;
            btnCrear.BackColor = Color.FromArgb(0, 120, 215);
            btnCrear.ForeColor = Color.White;
            btnCrear.FlatStyle = FlatStyle.Flat;

            btnCrear.Click += async (s, e) =>
            {
                try
                {
                    
                    if (string.IsNullOrWhiteSpace(txtJuego.Text) || txtJuego.Text == "Juego" ||
                        string.IsNullOrWhiteSpace(txtPuntuacion.Text) || txtPuntuacion.Text.Contains("Puntuacion") ||
                        string.IsNullOrWhiteSpace(txtComentario.Text) || txtComentario.Text == "Comentario")
                    {
                        MessageBox.Show("Todos los campos deben llenarse.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    if (!int.TryParse(txtPuntuacion.Text, out int puntuacion) || puntuacion < 0 || puntuacion > 10)
                    {
                        MessageBox.Show("La puntuacion debe ser un numero entre 0 y 10.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    ApiUsuarios api = new ApiUsuarios();

                    Resena nueva = new Resena()
                    {
                        usuario_id = Sesion.UsuarioId,
                        juego = txtJuego.Text,
                        puntuacion = puntuacion,
                        comentario = txtComentario.Text
                    };

                    await api.CrearResena(nueva);

                    MessageBox.Show("Resena creada");

                    new FormPrincipal().Show();
                    this.Hide();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error: " + ex.Message);
                }
            };

            div.Controls.Add(btnCrear);

            // Estilo del boton de volver al formPrincipal
            Button btnVolver = new Button();
            btnVolver.Text = "Volver";
            btnVolver.Width = 200;
            btnVolver.Height = 35;
            btnVolver.Left = (div.Width - btnVolver.Width) / 2;
            btnVolver.Top = btnCrear.Bottom + 10;
            btnVolver.BackColor = Color.Gray;
            btnVolver.ForeColor = Color.White;
            btnVolver.FlatStyle = FlatStyle.Flat;

            btnVolver.Click += (s, e) =>
            {
                new FormPrincipal().Show();
                this.Hide();
            };

            div.Controls.Add(btnVolver);

            
            this.BackgroundImage = Properties.Resources.er;
            this.BackgroundImageLayout = ImageLayout.Stretch;
            this.Icon = Properties.Resources.logo;
            this.Text = "Gamerating";
        }
    }
}
