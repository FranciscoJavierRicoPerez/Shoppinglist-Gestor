async function updateIsActiveShoppinglist(): Promise<boolean> {
  return await InMemory()
}

async function InMemory(): Promise<boolean> {
  return true
}

export { updateIsActiveShoppinglist }
