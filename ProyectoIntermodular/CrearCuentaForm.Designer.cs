namespace ProyectoIntermodular
{
    partial class CrearCuentaForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            btnCrearCuenta = new Button();
            txtConfirmPass = new TextBox();
            txtPass = new TextBox();
            txtCorreo = new TextBox();
            txtNombre = new TextBox();
            btnVolver = new Button();
            SuspendLayout();
            // 
            // btnCrearCuenta
            // 
            btnCrearCuenta.Location = new Point(745, 465);
            btnCrearCuenta.Name = "btnCrearCuenta";
            btnCrearCuenta.Size = new Size(102, 38);
            btnCrearCuenta.TabIndex = 0;
            btnCrearCuenta.Text = "Crear cuenta";
            btnCrearCuenta.UseVisualStyleBackColor = true;
            // 
            // txtConfirmPass
            // 
            txtConfirmPass.Location = new Point(747, 390);
            txtConfirmPass.Name = "txtConfirmPass";
            txtConfirmPass.Size = new Size(100, 23);
            txtConfirmPass.TabIndex = 1;
            // 
            // txtPass
            // 
            txtPass.Location = new Point(747, 328);
            txtPass.Name = "txtPass";
            txtPass.Size = new Size(100, 23);
            txtPass.TabIndex = 2;
            // 
            // txtCorreo
            // 
            txtCorreo.Location = new Point(747, 260);
            txtCorreo.Name = "txtCorreo";
            txtCorreo.Size = new Size(100, 23);
            txtCorreo.TabIndex = 3;
            // 
            // txtNombre
            // 
            txtNombre.Location = new Point(747, 195);
            txtNombre.Name = "txtNombre";
            txtNombre.Size = new Size(100, 23);
            txtNombre.TabIndex = 4;
            // 
            // btnVolver
            // 
            btnVolver.Location = new Point(713, 539);
            btnVolver.Name = "btnVolver";
            btnVolver.Size = new Size(175, 40);
            btnVolver.TabIndex = 5;
            btnVolver.Text = "button1";
            btnVolver.UseVisualStyleBackColor = true;
            // 
            // CrearCuentaForm
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1844, 704);
            Controls.Add(btnVolver);
            Controls.Add(txtNombre);
            Controls.Add(txtCorreo);
            Controls.Add(txtPass);
            Controls.Add(txtConfirmPass);
            Controls.Add(btnCrearCuenta);
            Name = "CrearCuentaForm";
            Text = "CrearCuentaForm";
            Load += CrearCuentaForm_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button btnCrearCuenta;
        private TextBox txtConfirmPass;
        private TextBox txtPass;
        private TextBox txtCorreo;
        private TextBox txtNombre;
        private Button btnVolver;
    }
}