export interface IProductImage  {
    id: number;
    name: string;
    priority: number;
}

export interface IProduct  {
    id: number;
    creationTime: string; // або `Date`, якщо потрібно працювати з об'єктами Date
    name: string;
    images: IProductImage[];
    description: string;
    categoryId: number;
    categoryName: string;
    price: string;
}


export interface IProductPostRequest {
    name: string;
    price: number;
    categoryId: string;
    imageFiles?: File[];
}
