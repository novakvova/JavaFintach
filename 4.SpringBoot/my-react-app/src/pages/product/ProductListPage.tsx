// src/pages/Home.tsx
// import {useEffect, useState} from "react";
// import {ICategory} from "../types/Category.ts";
// import axios from "axios";
import {APP_ENV} from "../../env";
import {Table} from "flowbite-react";
import {Link} from "react-router-dom";
import {useGetProductsQuery} from "../../services/apiProduct.ts";

const ProductListPage: React.FC = () => {

    const { data: list = [], isLoading, error } = useGetProductsQuery();

    if (isLoading) return <p>Loading...</p>;
    if (error) return <p>Error loading products.</p>;

    console.log("App is running!", APP_ENV.REMOTE_BASE_URL);

    console.log("product list", list);

    const mapData = list.map((product) => (
        <Table.Row key={product.id} className="bg-white dark:border-gray-700 dark:bg-gray-800">
            <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white">
                {product.name}
            </Table.Cell>
            <Table.Cell>
                {product.images.map((image) => (

                    <img src={`${APP_ENV.REMOTE_BASE_URL}/images/${image.name}`} alt={product.name}
                         style={{maxWidth: "100px"}}
                         />
                ))}
            </Table.Cell>
            <Table.Cell>{product.description}</Table.Cell>
            <Table.Cell>
                <a href="#" className="font-medium text-cyan-600 hover:underline dark:text-cyan-500">
                    Змінити
                </a>
            </Table.Cell>
        </Table.Row>
    ));

    return (
        <>
            <h1 className="text-center text-3xl font-bold">
                Продукти
            </h1>

            <div className={"mb-4"}>
                <Link to={"create"} className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition">
                    Додати продукт
                </Link>
            </div>



            <div className="overflow-x-auto">
                <Table>
                    <Table.Head>
                        <Table.HeadCell>Назва</Table.HeadCell>
                        <Table.HeadCell>Фото</Table.HeadCell>
                        <Table.HeadCell>Опис</Table.HeadCell>
                        <Table.HeadCell>
                            <span className="sr-only">Edit</span>
                        </Table.HeadCell>
                    </Table.Head>
                    <Table.Body className="divide-y">
                        {mapData}
                    </Table.Body>
                </Table>
            </div>
        </>
    )
};

export default ProductListPage;