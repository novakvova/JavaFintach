import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { APP_ENV } from "../env";
import {IProduct, IProductPostRequest} from "../types/Product.ts";
import {serialize} from "object-to-formdata";

export const apiProduct = createApi({
    reducerPath: "apiProduct",
    baseQuery: fetchBaseQuery({ baseUrl: APP_ENV.REMOTE_BASE_URL }),
    tagTypes: ["Product"], // Додаємо tag для категорій
    endpoints: (builder) => ({
        getProducts: builder.query<IProduct[], void>({
            query: () => "/api/products",
            providesTags: ["Product"], // Позначаємо, що цей запит пов'язаний з "Product"
        }),

        createProduct: builder.mutation<IProduct, IProductPostRequest>({
            query: (model) => {
                try {
                    const formData = serialize(model);
                    return {
                        url: '/api/products',
                        method: 'POST',
                        body: formData,
                    };
                } catch {
                    throw new Error("Error serializing the form data.");
                }
            },
            invalidatesTags: ["Product"], // Інвалідовуємо "Product" після створення
        }),
    }),
});

export const { useGetProductsQuery, useCreateProductMutation } = apiProduct;