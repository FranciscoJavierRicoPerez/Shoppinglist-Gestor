async function deleteShoppinglistData(id: number): Promise<boolean> {
  return await InMemory()
}

async function InMemory(): Promise<boolean> {
  return true
}

export { deleteShoppinglistData }
