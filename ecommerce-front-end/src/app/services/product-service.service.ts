import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Product } from '../common/product';
import { response } from 'express';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {
  private baseUrl = 'http://localhost:8080/products';
  constructor(private httpClient: HttpClient) { }
  getProductList(): Observable<Product[]>{
    return this.httpClient.get<ProductsResponse>(this.baseUrl).pipe(
      map(response => response._embedded.products)
    );
  }
}
interface ProductsResponse{
  _embedded: {
    products: Product[];
  };
}


