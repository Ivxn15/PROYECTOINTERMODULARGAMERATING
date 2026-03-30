namespace ProyectoIntermodular
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            btnLogin = new Button();
            txtPass = new TextBox();
            txtCorreo = new TextBox();
            btnCrearCuenta = new Button();
            SuspendLayout();
            // 
            // btnLogin
            // 
            btnLogin.Location = new Point(722, 504);
            btnLogin.Name = "btnLogin";
            btnLogin.Size = new Size(147, 44);
            btnLogin.TabIndex = 0;
            btnLogin.Text = "Iniciar Sesion";
            btnLogin.UseVisualStyleBackColor = true;
            btnLogin.Click += btnLogin_Click;
            // 
            // txtPass
            // 
            txtPass.Location = new Point(654, 368);
            txtPass.Name = "txtPass";
            txtPass.Size = new Size(285, 23);
            txtPass.TabIndex = 2;
            txtPass.Text = "Contrasena";
            // 
            // txtCorreo
            // 
            txtCorreo.Location = new Point(654, 253);
            txtCorreo.Name = "txtCorreo";
            txtCorreo.Size = new Size(285, 23);
            txtCorreo.TabIndex = 1;
            txtCorreo.Text = "Correo";
            // 
            // btnCrearCuenta
            // 
            btnCrearCuenta.Location = new Point(722, 657);
            btnCrearCuenta.Name = "btnCrearCuenta";
            btnCrearCuenta.Size = new Size(147, 40);
            btnCrearCuenta.TabIndex = 3;
            btnCrearCuenta.Text = "Crear Cuenta";
            btnCrearCuenta.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1613, 798);
            Controls.Add(btnCrearCuenta);
            Controls.Add(txtPass);
            Controls.Add(txtCorreo);
            Controls.Add(btnLogin);
            Name = "Form1";
            Text = "Form1";
            Load += Form1_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button btnLogin;
        private TextBox txtPass;
        private TextBox txtCorreo;
        private Button btnCrearCuenta;
    }
}
