namespace ProyectoIntermodular
{
    partial class FormPrincipal
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
            panelResenas = new Panel();
            div = new Panel();
            btnCrearResena = new Button();
            btnCerrarSesion = new Button();
            div.SuspendLayout();
            SuspendLayout();
            // 
            // panelResenas
            // 
            panelResenas.Location = new Point(125, 149);
            panelResenas.Name = "panelResenas";
            panelResenas.Size = new Size(631, 415);
            panelResenas.TabIndex = 0;
            panelResenas.Paint += panelResenas_Paint;
            // 
            // div
            // 
            div.Controls.Add(btnCerrarSesion);
            div.Controls.Add(panelResenas);
            div.Controls.Add(btnCrearResena);
            div.Location = new Point(359, 12);
            div.Name = "div";
            div.Size = new Size(898, 639);
            div.TabIndex = 6;
            // 
            // btnCrearResena
            // 
            btnCrearResena.Location = new Point(336, 12);
            btnCrearResena.Name = "btnCrearResena";
            btnCrearResena.Size = new Size(141, 42);
            btnCrearResena.TabIndex = 7;
            btnCrearResena.Text = "button1";
            btnCrearResena.UseVisualStyleBackColor = true;
            // 
            // btnCerrarSesion
            // 
            btnCerrarSesion.Location = new Point(336, 72);
            btnCerrarSesion.Name = "btnCerrarSesion";
            btnCerrarSesion.Size = new Size(141, 47);
            btnCerrarSesion.TabIndex = 9;
            btnCerrarSesion.Text = "button1";
            btnCerrarSesion.UseVisualStyleBackColor = true;
            // 
            // FormPrincipal
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1609, 796);
            Controls.Add(div);
            Name = "FormPrincipal";
            Text = "FormPrincipal";
            Load += FormPrincipal_Load;
            div.ResumeLayout(false);
            ResumeLayout(false);
        }

        #endregion

        private Panel panelResenas;
        private Panel div;
        private Button btnCrearResena;
        private Button btnCerrarSesion;
    }
}