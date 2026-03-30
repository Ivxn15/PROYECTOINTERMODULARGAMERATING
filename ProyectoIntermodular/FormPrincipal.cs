using System;
using System.Collections.Generic;
using System.Drawing;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ProyectoIntermodular
{
    public partial class FormPrincipal : Form
    {
        public FormPrincipal()
        {
            InitializeComponent();
            this.DoubleBuffered = true;
        }

        private async void FormPrincipal_Load(object sender, EventArgs e)
        {
            //Estilos de la ventana principal
            this.Size = new Size(1920, 1080);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.FormBorderStyle = FormBorderStyle.Sizable;
            this.ControlBox = true;
            this.FormClosing += (s, ev) => Application.Exit();
            //Estilos del Panel mas grande donde va todo el contenido dentro
            div.Size = new Size(1000, 700);
            div.BackColor = Color.FromArgb(45, 45, 45);
            div.Left = (this.ClientSize.Width - div.Width) / 2;
            div.Top = (this.ClientSize.Height - div.Height) / 2;
            this.Controls.Add(div);

            // Estilos del boton de crear la resena
            btnCrearResena.Text = "Crear Resena";
            btnCrearResena.Size = new Size(200, 40);
            btnCrearResena.Left = (div.Width - btnCrearResena.Width) / 2;
            btnCrearResena.Top = 20;
            btnCrearResena.FlatStyle = FlatStyle.Flat;
            btnCrearResena.BackColor = Color.FromArgb(0, 120, 215);
            btnCrearResena.ForeColor = Color.White;
            btnCrearResena.Font = new Font("JetBrains Mono", 12, FontStyle.Bold);
            btnCrearResena.Click += (s, ev) =>
            {
                new CrearResenaForm().Show();
                this.Hide();
            };
            div.Controls.Add(btnCrearResena);

            // Estlos del boton de cerrar sesion
            btnCerrarSesion.Text = "Cerrar Sesión";
            btnCerrarSesion.Size = new Size(200, 40);
            btnCerrarSesion.Left = (div.Width - btnCerrarSesion.Width) / 2;
            btnCerrarSesion.Top = btnCrearResena.Bottom + 10;
            btnCerrarSesion.FlatStyle = FlatStyle.Flat;
            btnCerrarSesion.BackColor = Color.FromArgb(200, 0, 0);
            btnCerrarSesion.ForeColor = Color.White;
            btnCerrarSesion.Font = new Font("JetBrains Mono", 12, FontStyle.Bold);
            btnCerrarSesion.Click += (s, ev) =>
            {
                new Form1().Show();
                this.Hide();
            };
            div.Controls.Add(btnCerrarSesion);

            // Estilo del panel de las resenas
            panelResenas.Size = new Size(div.Width - 40, div.Height - btnCerrarSesion.Bottom - 30);
            panelResenas.Left = 20;
            panelResenas.Top = btnCerrarSesion.Bottom + 10;
            panelResenas.AutoScroll = true;
            panelResenas.BackColor = Color.FromArgb(50, 50, 50);
            div.Controls.Add(panelResenas);

            ApiUsuarios api = new ApiUsuarios();
            List<Resena> resenas = await api.GetTodasResenas();

            int topPos = 10;
            int usuarioActualId = Sesion.UsuarioId;

            foreach (var r in resenas)
            {
                //Estilo del panel de Cada resena
                Panel resenaPanel = new Panel();
                resenaPanel.Size = new Size(panelResenas.Width - 20, 130);
                resenaPanel.Left = 10;
                resenaPanel.Top = topPos;
                resenaPanel.BackColor = Color.FromArgb(70, 70, 70);

                int margenPorElCheckbox = resenaPanel.Width - 300;
                //Estilos etiqueta del nombre del juego y el del usuariow
                Label lblJuego = new Label();
                lblJuego.Text = $"Juego: {r.juego} - El usuario {r.nombre} posteo esta resena";
                lblJuego.ForeColor = Color.White;
                lblJuego.Font = new Font("JetBrains Mono", 12, FontStyle.Bold);
                lblJuego.Size = new Size(margenPorElCheckbox, 20);
                lblJuego.Left = 10;
                lblJuego.Top = 10;
                resenaPanel.Controls.Add(lblJuego);
                //Estilos etiqueta de puntuacion del juego
                Label lblPuntuacion = new Label();
                lblPuntuacion.Text = $"Puntuación: {r.puntuacion}/10";
                lblPuntuacion.ForeColor = Color.White;
                lblPuntuacion.Font = new Font("JetBrains Mono", 11);
                lblPuntuacion.Size = new Size(margenPorElCheckbox, 20);
                lblPuntuacion.Left = 10;
                lblPuntuacion.Top = 35;
                resenaPanel.Controls.Add(lblPuntuacion);
                //Estilos de etiqueta comentario del juego
                Label lblComentario = new Label();
                lblComentario.Text = $"Comentario: {r.comentario}";
                lblComentario.ForeColor = Color.White;
                lblComentario.Font = new Font("JetBrains Mono", 11);
                lblComentario.Size = new Size(margenPorElCheckbox, 40);
                lblComentario.Left = 10;
                lblComentario.Top = 60;
                resenaPanel.Controls.Add(lblComentario);
                //Estilo de etiqueta de la fecha en la cual se hizo la resena
                Label lblFecha = new Label();
                lblFecha.Text = $"Fecha: {r.fecha}";
                lblFecha.ForeColor = Color.Gray;
                lblFecha.Font = new Font("JetBrains Mono", 10, FontStyle.Italic);
                lblFecha.Size = new Size(margenPorElCheckbox, 20);
                lblFecha.Left = 10;
                lblFecha.Top = 100;
                resenaPanel.Controls.Add(lblFecha);

                
                if (r.usuario_id == usuarioActualId)
                {
                    //Estilos del checkbox que va en cada resena que se muestra MIA
                    CheckBox chkEliminar = new CheckBox();
                    chkEliminar.Size = new Size(20, 20);
                    chkEliminar.Left = resenaPanel.Width - 30;
                    chkEliminar.Top = 15;
                    chkEliminar.BackColor = Color.FromArgb(70, 70, 70);
                    resenaPanel.Controls.Add(chkEliminar);
                    //Estilos del bootn de eliminar la resena 
                    Button btnEliminar = new Button();
                    btnEliminar.Text = "Eliminar";
                    btnEliminar.Size = new Size(100, 35);
                    btnEliminar.Left = resenaPanel.Width - 120;
                    btnEliminar.Top = 60;
                    btnEliminar.BackColor = Color.FromArgb(200, 0, 0);
                    btnEliminar.ForeColor = Color.White;
                    btnEliminar.FlatStyle = FlatStyle.Flat;

                    btnEliminar.Click += async (s, ev) =>
                    {
                        if (!chkEliminar.Checked)
                        {
                            MessageBox.Show("Selecciona la resena primero");
                            return;
                        }

                        var confirm = MessageBox.Show("¿Seguro que quieres eliminar la resena?", "Confirmar", MessageBoxButtons.YesNo);

                        if (confirm == DialogResult.Yes)
                        {
                            await api.EliminarResena(r.id, usuarioActualId);
                            panelResenas.Controls.Remove(resenaPanel);
                        }
                    };

                    resenaPanel.Controls.Add(btnEliminar);
                }

                panelResenas.Controls.Add(resenaPanel);
                topPos += resenaPanel.Height + 10;
            }

            this.BackgroundImage = Properties.Resources.cyberpunk;
            this.BackgroundImageLayout = ImageLayout.Stretch;
            this.Icon = Properties.Resources.logo;
            this.Text = "Gamerating";
        }
    


        private void panelResenas_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
