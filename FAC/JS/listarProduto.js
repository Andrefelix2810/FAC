$(document).ready(function() {
    const apiUrl = 'http://localhost:8080/produtos';
    let currentPage = 1;
    let totalPages = 1;

    function fetchProducts(page = 1, searchTerm = '') {
        $.ajax({
            url: `${apiUrl}/buscar`,
            method: 'GET',
            data: {
                nome: searchTerm,
                page: page - 1,
                size: 10
            },
            success: function(response) {
                renderProducts(response.content);
                totalPages = response.totalPages;
                renderPagination();
            },
            error: function(error) {
                console.error('Erro ao buscar produtos:', error);
            }
        });
    }

    function renderProducts(products) {
        const productTableBody = $('#productTableBody');
        productTableBody.empty();
        products.forEach(product => {
            const productRow = `
                <tr>
                    <td>${product.id}</td>
                    <td>${product.nome}</td>
                    <td>${product.qtdEstoque}</td>
                    <td>${product.preco}</td>
                    <td>${product.ativo ? 'Ativo' : 'Inativo'}</td>
                    <td>
                        <button class="btn btn-sm btn-warning" onclick="editProduct(${product.id})">Alterar</button>
                        <button class="btn btn-sm btn-secondary" onclick="toggleStatus(${product.id}, ${product.ativo})">
                            ${product.ativo ? 'Inativar' : 'Reativar'}
                        </button>
                    </td>
                </tr>
            `;
            productTableBody.append(productRow);
        });
    }

    function renderPagination() {
        const pagination = $('#pagination');
        pagination.empty();
        for (let i = 1; i <= totalPages; i++) {
            const pageItem = `
                <li class="page-item ${i === currentPage ? 'active' : ''}">
                    <a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
                </li>
            `;
            pagination.append(pageItem);
        }
    }

    $('#searchInput').on('input', function() {
        const searchTerm = $(this).val();
        currentPage = 1;
        fetchProducts(currentPage, searchTerm);
    });

    $('#addProductBtn').on('click', function() {
        window.location.href = 'produto.html';
    });

    window.changePage = function(page) {
        currentPage = page;
        fetchProducts(currentPage, $('#searchInput').val());
    };

    window.editProduct = function(productId) {
        window.location.href = `alterarProduto.html?id=${productId}`;
    };

    window.toggleStatus = function(productId, currentStatus) {
        const newStatus = !currentStatus;
        $.ajax({
            url: `${apiUrl}/${productId}/alterarStatus`,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({ ativo: newStatus }),
            success: function() {
                fetchProducts(currentPage, $('#searchInput').val());
            },
            error: function(error) {
                console.error('Erro ao alterar status do produto:', error);
            }
        });
    };

    fetchProducts();
});