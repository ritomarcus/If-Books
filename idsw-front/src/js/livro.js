$(document).ready(function () {
  let livroEditandoId = null;

  function carregarLivros() {
    $.ajax({
      url: 'http://localhost:8080/livros',
      method: 'GET',
      success: function (livros) {
        preencherTabelaLivros(livros);
      },
      error: function () {
        alert('Erro ao carregar livros.');
      }
    });
  }

  function preencherTabelaLivros(livros) {
    const tbody = $('.livros-tabela tbody');
    tbody.empty();
    livros.forEach(livro => {
      tbody.append(`
        <tr>
          <td>${livro.id}</td>
          <td>${livro.nome}</td>
          <td>${livro.editora}</td>
          <td>${livro.dataPublicacao}</td>
          <td>${livro.resumo}</td>
          <td>${livro.autor ? livro.autor.nome : ''}</td>
          <td>
            <button class="btn btn-warning btn-sm me-1 btn-editar-livro"
              data-id="${livro.id}"
              data-nome="${livro.nome}"
              data-editora="${livro.editora}"
              data-data-publicacao="${livro.dataPublicacao}"
              data-resumo="${livro.resumo}"
              data-autor-id="${livro.autor ? livro.autor.id : ''}">
              <i class="bi bi-pencil"></i>
            </button>
            <button class="btn btn-danger btn-sm btn-deletar-livro" data-id="${livro.id}">
              <i class="bi bi-trash"></i>
            </button>
          </td>
        </tr>
      `);
    });
  }

  // Pesquisa por ID
  $('#btnPesquisarIdLivro').click(function () {
    const id = $('#inputPesquisaIdLivro').val().trim();
    if (!id) {
      alert('Informe um ID válido para pesquisa.');
      return;
    }

    $.ajax({
      url: `http://localhost:8080/livros/${id}`,
      method: 'GET',
      success: function (livro) {
        preencherTabelaLivros([livro]);
      },
      error: function () {
        alert('Livro não encontrado.');
        preencherTabelaLivros([]);
      }
    });
  });

  // Popular select autores no modal
  function popularSelectAutores() {
    $.ajax({
      url: 'http://localhost:8080/autores',
      method: 'GET',
      success: function (autores) {
        const select = $('#autorLivro');
        select.empty();
        select.append('<option value="">Selecione um autor</option>');
        autores.forEach(autor => {
          select.append(`<option value="${autor.id}">${autor.nome}</option>`);
        });
      },
      error: function () {
        alert('Erro ao carregar autores para seleção.');
      }
    });
  }

  // Abrir modal para adicionar livro
  $('#modalCadastroLivro').on('show.bs.modal', function () {
    // Só zera o ID e altera o título se NÃO estiver editando
    if (!livroEditandoId) {
      $('#formLivro')[0].reset();
      $('#modalCadastroLivroLabel').text('Cadastrar Novo Livro');
    }
    popularSelectAutores();
    $('#formLivro').removeClass('was-validated');
  });

  $('#btnAdicionarLivro').click(function () {
    livroEditandoId = null;
  });


  // Abrir modal para editar livro
  $(document).on('click', '.btn-editar-livro', function () {
    livroEditandoId = $(this).data('id');
    $('#nomeLivro').val($(this).data('nome'));
    $('#editoraLivro').val($(this).data('editora'));
    $('#dataPublicacaoLivro').val($(this).data('data-publicacao'));
    $('#resumoLivro').val($(this).data('resumo'));
    $('#modalCadastroLivroLabel').text('Editar Livro');
    popularSelectAutores();
    $('#formLivro').removeClass('was-validated');
    $('#modalCadastroLivro').modal('show');
  });

  // Enviar formulário (criar ou atualizar)
  $('#formLivro').submit(function (e) {
    e.preventDefault();

    if (!this.checkValidity()) {
      e.stopPropagation();
      $(this).addClass('was-validated');
      return;
    }

    const livro = {
      nome: $('#nomeLivro').val(),
      editora: $('#editoraLivro').val(),
      dataPublicacao: $('#dataPublicacaoLivro').val(),  // <-- Sem converter, envia exatamente como digitado
      resumo: $('#resumoLivro').val(),
      autor: { id: $('#autorLivro').val() }
    };


    const url = livroEditandoId
      ? `http://localhost:8080/livros/${livroEditandoId}`
      : 'http://localhost:8080/livros';
    const method = livroEditandoId ? 'PUT' : 'POST';

    $.ajax({
      url,
      method,
      contentType: 'application/json',
      data: JSON.stringify(livro),
      success: function () {
        alert(livroEditandoId ? 'Livro atualizado com sucesso!' : 'Livro cadastrado com sucesso!');
        $('#modalCadastroLivro').modal('hide');
        carregarLivros();
      },
      error: function (xhr) {
        alert('Erro ao salvar o livro: ' + xhr.responseText);
        console.error(xhr);
      }
    });
  });

  // Deletar livro
  $(document).on('click', '.btn-deletar-livro', function () {
    const id = $(this).data('id');
    if (confirm('Tem certeza que deseja excluir este livro?')) {
      $.ajax({
        url: `http://localhost:8080/livros/${id}`,
        method: 'DELETE',
        success: function () {
          alert('Livro excluído com sucesso!');
          carregarLivros();
        },
        error: function (xhr) {
          alert('Erro ao excluir livro: ' + xhr.responseText);
          console.error(xhr);
        }
      });
    }
  });

  // Carrega lista inicial de livros
  carregarLivros();

  // Aplica máscara no campo de data
  $('#dataPublicacaoLivro').mask('00/00/0000');
});
