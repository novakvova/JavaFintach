// src/pages/Home.tsx
// import {useEffect, useState} from "react";
// import {ICategory} from "../types/Category.ts";
// import axios from "axios";
import {APP_ENV} from "../env";
import {Table} from "flowbite-react";
import {useGetCategoriesQuery} from "../services/apiCategory.ts";
import {Link} from "react-router-dom";

const Home: React.FC = () => {

    const { data: list = [], isLoading, error } = useGetCategoriesQuery();

    if (isLoading) return <p>Loading...</p>;
    if (error) return <p>Error loading categories.</p>;

    console.log("App is running!", APP_ENV.REMOTE_BASE_URL);

    const mapData = list.map((category) => (
        <Table.Row key={category.id} className="bg-white dark:border-gray-700 dark:bg-gray-800">
            <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white">
                {category.name}
            </Table.Cell>
            <Table.Cell>
                <img src={`${APP_ENV.REMOTE_BASE_URL}/images/${category.image}`} alt={category.name}
                 className={"w-16 h-16 object-cover rounded"}/>
            </Table.Cell>
            <Table.Cell>{category.description}</Table.Cell>
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
                Категорії
            </h1>

            <div className={"mb-4"}>
                <Link to={"/category/create"} className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition">
                    Додати категорію
                </Link>
            </div>



            <div className="overflow-x-auto">
                <Table>
                    <Table.Head>
                        <Table.HeadCell>Назва категорії</Table.HeadCell>
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

export default Home;