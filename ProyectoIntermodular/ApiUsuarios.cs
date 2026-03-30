using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

public class ApiUsuarios
{
    private static readonly HttpClient client = new HttpClient();
    private string baseUrl = "http://localhost:8080/tema5maven/rest/usuarios";

    // El inciio de sesion
    public async Task<int> Login(string correo, string contrasena)
    {
        var datos = new
        {
            correo = correo,
            contrasena = contrasena
        };

        var json = JsonSerializer.Serialize(datos);
        var content = new StringContent(json, Encoding.UTF8, "application/json");

        var response = await client.PostAsync(baseUrl + "/login", content);

        if (response.IsSuccessStatusCode)
        {
            var responseString = await response.Content.ReadAsStringAsync();
            var doc = JsonDocument.Parse(responseString);
            return doc.RootElement.GetProperty("id").GetInt32();
        }
        else
        {
            throw new Exception("Login incorrecto");
        }
    }

    // RRegistro mi cuenta
    public async Task<int> Registrar(string nombre, string correo, string contrasena)
    {
        var datos = new
        {
            nombre = nombre,
            correo = correo,
            contrasena = contrasena
        };

        var json = JsonSerializer.Serialize(datos);
        var content = new StringContent(json, Encoding.UTF8, "application/json");

        var response = await client.PostAsync(baseUrl, content);

        if (response.IsSuccessStatusCode)
        {
            var responseString = await response.Content.ReadAsStringAsync();
            var doc = JsonDocument.Parse(responseString);
            return doc.RootElement.GetProperty("id").GetInt32();
        }
        else
        {
            throw new Exception("Error en registro");
        }
    }

    // Creo la resena
    public async Task<int> CrearResena(Resena r)
    {
        var json = JsonSerializer.Serialize(r);
        var content = new StringContent(json, Encoding.UTF8, "application/json");

        var response = await client.PostAsync(baseUrl + "/resenas", content);

        if (response.IsSuccessStatusCode)
        {
            var responseString = await response.Content.ReadAsStringAsync();
            return int.Parse(responseString);
        }
        else
        {
            throw new Exception("Error al crear reseña");
        }
    }

    // Obtengo todas las resenas
    public async Task<List<Resena>> GetTodasResenas()
    {
        var response = await client.GetAsync(baseUrl + "/resenas/todas");

        if (response.IsSuccessStatusCode)
        {
            var json = await response.Content.ReadAsStringAsync();
            return JsonSerializer.Deserialize<List<Resena>>(json);
        }
        else
        {
            throw new Exception("Error al obtener reseñas");
        }
    }

    // Elimino las resenas
    public async Task EliminarResena(int id, int usuarioId)
    {
        var response = await client.DeleteAsync(baseUrl + $"/resenas/{id}?usuarioId={usuarioId}");

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception("No puedes eliminar esta reseña");
        }
    }
}
